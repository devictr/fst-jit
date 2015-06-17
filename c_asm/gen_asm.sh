#!/bin/bash

FST="$1"
oIFS=$IFS
: ${ASM_SWITCH:=1}
(( ASM_SWITCH == 0 )) && unset ASM_SWITCH

DO_SWITCH=
: ${SWITCH_LIMIT:=2}

sort "$FST" > "$FST.sort"

FIRST_CALL=1

compute_switch() {
    ARR_ASCII=( $(awk 'BEGIN{for(n=0;n<256;n++)ord[sprintf("%c",n)]=n}
    /^'$DEP'/{print ord[$3]}' "$FST.sort" | sort) )
}

next() {
    PREV_DEP=$DEP
    FIRST_CALL=

    continue
}

cat <<EOF
	.file	"${FST%.*}.c"
	.text
	.globl	compute_fst
	.type	compute_fst, @function

compute_fst:
	pushq	%rbp            # remember old base pointer
	movq	%rsp, %rbp      # set new base pointer
	movq	%rdi, -24(%rbp) # put content of rdi (token) in -24(%rbp)
	movl	\$0, -8(%rbp)    # pos   (-8(%rbp)) = 0
	movl	\$0, -4(%rbp)    # total (-4(%rbp)) = 0

EOF

while read DEP ARR CHAR WEIGHT ; do
    # If it's a final node
    if [[ ! "$CHAR" ]]; then
        if [[ ${#tmp[@]} != 0 ]] ; then
            IFS=$'\n'
            echo "${tmp[*]}"
            IFS=$oIFS
            tmp=()
        fi

        WEIGHT=${ARR:-0}

        [[ ! "$DO_SWITCH" ]] &&
            cat <<EOF
	movl \$-1, %eax          # default : return -1
	jmp .RET
EOF
        echo -e ".NODE_$DEP:"

    (( WEIGHT != 0 )) &&
        echo "	addl  \$$WEIGHT, -4(%rbp)         # total += Weight"
    echo "	jmp .END                # goto END"

        continue
    fi

    : ${WEIGHT:=0}

    # Change of node
    if [[ $DEP != $PREV_DEP ]]; then
        if [[ ! "$FIRST_CALL" && ! "$DO_SWITCH" ]]; then
            cat <<EOF
	movl \$-1, %eax          # default : return -1
	jmp .RET

EOF
        fi

        if [[ ${#tmp[@]} != 0 ]] ; then
            IFS=$'\n'
            echo "${tmp[*]}"
            IFS=$oIFS
            tmp=()
        fi

        cat <<EOF
.NODE_$DEP:
	addl	\$1, -8(%rbp)    # pos++
	movl	-8(%rbp), %eax  # eax = pos
	leaq	-1(%rax), %rdx  # rdx = pos - 1
	movq	-24(%rbp), %rax # load token (address) in rax
	addq	%rdx, %rax      # rax = &(token[pos-1])
	movzbl	(%rax), %eax    # eax = token[pos-1]

EOF
        if [[ "$ASM_SWITCH" ]] ; then
            DO_SWITCH=
            compute_switch
            (( ${#ARR_ASCII[@]} > SWITCH_LIMIT )) &&
                DO_SWITCH=1
        fi
    fi

    printf -v CHAR_INT '%d' "\"$CHAR"


    [[ ! "$DO_SWITCH" ]] &&
        echo "	cmpl \$$CHAR_INT, %eax          # case '$CHAR'"

    if (( WEIGHT != 0 || DO_SWITCH == 1 )) ; then
        tmp+=(
            ".NODE_${DEP}_$CHAR:"
            "	addl  \$$WEIGHT, -4(%rbp)      # total += $WEIGHT"
            "	jmp .NODE_$ARR"
            ""
            )

        if [[ "$DO_SWITCH" ]]; then
            [[ $DEP == $PREV_DEP ]] && next

            echo "	subl    \$${ARR_ASCII[0]}, %eax       # eax -= '$(printf '%c' "$CHAR")'"
            # MAX - MIN
            CHAR_RANGE=$(( ARR_ASCII[${#ARR_ASCII[@]}-1] - ARR_ASCII[0] ))

            cat <<EOF
	cmpl \$$CHAR_RANGE, %eax           # eax = '$(printf '%c' "$(printf "\x$(printf "%x" ${ARR_ASCII[${#ARR_ASCII[@]}-1]})")")' - '$(printf "\x$(printf "%x" ${ARR_ASCII[0]})")' (max - min)
	ja .END
	movq .NODE_${DEP}_SW(,%rax,8), %rax
	jmp *%rax

    .section .rodata
.NODE_${DEP}_SW:
EOF
            j=0
            for (( i = 0; i <= (ARR_ASCII[${#ARR_ASCII[@]}-1] - ARR_ASCII[0]) ; i++ )); do
                if (( ARR_ASCII[0] + i == ARR_ASCII[j] )) ; then
                    echo "	.quad .NODE_${DEP}_$(printf "\x$(printf "%x" ${ARR_ASCII[j]})")         # '$(printf "\x$(printf "%x" ${ARR_ASCII[j]})")'"
                    (( j++ ))
                else
                    echo "	.quad .ERR              # '$(printf "\x$(printf "%x" $(( ARR_ASCII[0] + i )))")'"
                fi
            done

            echo -e "	.text\n"
        else
            echo "	je .NODE_${DEP}_$CHAR"
        fi
    else
        echo "	je .NODE_$ARR"
    fi

    next
done < "$FST.sort"

cat <<EOF

.ERR:
	movl \$-1, %eax          # return -1
	jmp .RET
.END:
	movl    -4(%rbp), %eax  # Put return value in eax
.RET:
	popq	%rbp
	ret
	.size	compute_fst, .-compute_fst
	.section	.rodata

.PRINTF_FMT:
	.string	"%d\n"
	.text
	.globl	main
	.type	main, @function
main:
	pushq	%rbp
	movq	%rsp, %rbp
	subq	\$16, %rsp
	movl	%edi, -4(%rbp)
	movq	%rsi, -16(%rbp)
	cmpl	\$1, -4(%rbp)        # if (argc < 2)
	jg	.DO_MAIN
	movl	\$1, %eax            # return 1;
	jmp	.END_MAIN
.DO_MAIN:
	movq	-16(%rbp), %rax     # rax = &(argv[0])
	addq	\$8, %rax            # rax = &(argv[1])
	movq	(%rax), %rax        # rax = argv[1]
	movq	%rax, %rdi          # rdi = argv[1]
	call	compute_fst
	movl	%eax, %esi          # esi = compute_fst()
	movl	\$.PRINTF_FMT, %edi
	movl	\$0, %eax
	call	printf
	movl	\$0, %eax
.END_MAIN:
	leave
	ret
	.size	main, .-main
	.ident	"GCC: (GNU) 4.9.2 20150212 (Red Hat 4.9.2-6)"
	.section	.note.GNU-stack,"",@progbits
EOF

rm "$FST.sort"
