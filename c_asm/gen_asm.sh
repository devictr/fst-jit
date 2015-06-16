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

cat <<EOF
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
