#!/bin/bash

FST="$1"

sort "$FST" > "$FST.sort"

FIRST_CALL=1

cat <<EOF
	.file	"3.c"
	.text
	.globl	compute_fst
	.type	compute_fst, @function

compute_fst:
	pushq	%rbp             # remember old base pointer
	movq	%rsp, %rbp       # set new base pointer
	movq	%rdi, -24(%rbp)  # put content of rdi (token) in -24(%rbp)
	movl	\$0, -8(%rbp)    # pos   (-8(%rbp)) = 0
	movl	\$0, -4(%rbp)    # total (-4(%rbp)) = 0

EOF

while read DEP ARR CHAR WEIGHT ; do
    : ${WEIGHT:=0}

    if [[ $DEP != $PREV_DEP ]]; then
        if [[ ! "$FIRST_CALL" ]]; then
            cat <<EOF
	movl \$-1, %eax          # default : return -1
	jmp .RET

EOF
        fi

        cat <<EOF
.NODE_$DEP:
	addl	\$1, -8(%rbp)   # pos++
	movl	-8(%rbp), %eax  # eax = pos
	leaq	-1(%rax), %rdx  # rdx = pos - 1
	movq	-24(%rbp), %rax # load token (address) in rax
	addq	%rdx, %rax      # rax = &(token[pos-1])
	movzbl	(%rax), %eax    # eax = token[pos-1]

EOF
    fi

    PREV_DEP=$DEP
    FIRST_CALL=
done < "$FST.sort"

cat <<EOF

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
	cmpl	\$1, -4(%rbp)
	jg	.DO_MAIN
	movl	\$1, %eax
	jmp	.END_MAIN
.DO_MAIN:
	movq	-16(%rbp), %rax
	addq	\$8, %rax
	movq	(%rax), %rax
	movq	%rax, %rdi
	call	compute_fst
	movl	%eax, %esi
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
