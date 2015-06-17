	.file	"3.c"
	.text
	.globl	compute_fst
	.type	compute_fst, @function

compute_fst:
	pushq	%rbp             # remember old base pointer
	movq	%rsp, %rbp       # set new base pointer
	movq	%rdi, -24(%rbp)  # put content of rdi (token) in -24(%rbp)
	movl	$0, -8(%rbp)    # pos   (-8(%rbp)) = 0
	movl	$0, -4(%rbp)    # total (-4(%rbp)) = 0

.NODE_0:
	addl	$1, -8(%rbp)   # pos++
	movl	-8(%rbp), %eax  # eax = pos
	leaq	-1(%rax), %rdx  # rdx = pos - 1
	movq	-24(%rbp), %rax # load token (address) in rax
	addq	%rdx, %rax      # rax = &(token[pos-1])
	movzbl	(%rax), %eax    # eax = token[pos-1]

	cmpl $77, %eax      # case '77'
	je .NODE_1
	cmpl $80, %eax      # case '80'
	je .NODE_0_80
	cmpl $84, %eax      # case '84'
	je .NODE_0_84
	cmpl $83, %eax      # case '83'
	je .NODE_0_83
	movl $-1, %eax          # default : return -1
	jmp .RET

.NODE_0_80:
	addl  $2, -4(%rbp)         # total += 2
	jmp .NODE_4

.NODE_0_84:
	addl  $5, -4(%rbp)         # total += 5
	jmp .NODE_4

.NODE_0_83:
	addl  $3, -4(%rbp)         # total += 3
	jmp .NODE_6

.NODE_1:
	addl	$1, -8(%rbp)   # pos++
	movl	-8(%rbp), %eax  # eax = pos
	leaq	-1(%rax), %rdx  # rdx = pos - 1
	movq	-24(%rbp), %rax # load token (address) in rax
	addq	%rdx, %rax      # rax = &(token[pos-1])
	movzbl	(%rax), %eax    # eax = token[pos-1]

	cmpl $79, %eax      # case '79'
	je .NODE_2
	movl $-1, %eax          # default : return -1
	jmp .RET

.NODE_2:
	addl	$1, -8(%rbp)   # pos++
	movl	-8(%rbp), %eax  # eax = pos
	leaq	-1(%rax), %rdx  # rdx = pos - 1
	movq	-24(%rbp), %rax # load token (address) in rax
	addq	%rdx, %rax      # rax = &(token[pos-1])
	movzbl	(%rax), %eax    # eax = token[pos-1]

	cmpl $84, %eax      # case '84'
	je .NODE_2_84
	cmpl $80, %eax      # case '80'
	je .NODE_9
	movl $-1, %eax          # default : return -1
	jmp .RET

.NODE_2_84:
	addl  $1, -4(%rbp)         # total += 1
	jmp .NODE_3

.NODE_3:
	addl	$1, -8(%rbp)   # pos++
	movl	-8(%rbp), %eax  # eax = pos
	leaq	-1(%rax), %rdx  # rdx = pos - 1
	movq	-24(%rbp), %rax # load token (address) in rax
	addq	%rdx, %rax      # rax = &(token[pos-1])
	movzbl	(%rax), %eax    # eax = token[pos-1]

	cmpl $72, %eax      # case '72'
	je .NODE_9
	movl $-1, %eax          # default : return -1
	jmp .RET

.NODE_4:
	addl	$1, -8(%rbp)   # pos++
	movl	-8(%rbp), %eax  # eax = pos
	leaq	-1(%rax), %rdx  # rdx = pos - 1
	movq	-24(%rbp), %rax # load token (address) in rax
	addq	%rdx, %rax      # rax = &(token[pos-1])
	movzbl	(%rax), %eax    # eax = token[pos-1]

	cmpl $79, %eax      # case '79'
	je .NODE_5
	movl $-1, %eax          # default : return -1
	jmp .RET

.NODE_5:
	addl	$1, -8(%rbp)   # pos++
	movl	-8(%rbp), %eax  # eax = pos
	leaq	-1(%rax), %rdx  # rdx = pos - 1
	movq	-24(%rbp), %rax # load token (address) in rax
	addq	%rdx, %rax      # rax = &(token[pos-1])
	movzbl	(%rax), %eax    # eax = token[pos-1]

	cmpl $80, %eax      # case '80'
	je .NODE_9
	movl $-1, %eax          # default : return -1
	jmp .RET

.NODE_6:
	addl	$1, -8(%rbp)   # pos++
	movl	-8(%rbp), %eax  # eax = pos
	leaq	-1(%rax), %rdx  # rdx = pos - 1
	movq	-24(%rbp), %rax # load token (address) in rax
	addq	%rdx, %rax      # rax = &(token[pos-1])
	movzbl	(%rax), %eax    # eax = token[pos-1]

	cmpl $84, %eax      # case '84'
	je .NODE_7
	movl $-1, %eax          # default : return -1
	jmp .RET

.NODE_7:
	addl	$1, -8(%rbp)   # pos++
	movl	-8(%rbp), %eax  # eax = pos
	leaq	-1(%rax), %rdx  # rdx = pos - 1
	movq	-24(%rbp), %rax # load token (address) in rax
	addq	%rdx, %rax      # rax = &(token[pos-1])
	movzbl	(%rax), %eax    # eax = token[pos-1]

	cmpl $79, %eax      # case '79'
	je .NODE_7_79
	cmpl $65, %eax      # case '65'
	je .NODE_8
	movl $-1, %eax          # default : return -1
	jmp .RET

.NODE_7_79:
	addl  $1, -4(%rbp)         # total += 1
	jmp .NODE_5

.NODE_8:
	addl	$1, -8(%rbp)   # pos++
	movl	-8(%rbp), %eax  # eax = pos
	leaq	-1(%rax), %rdx  # rdx = pos - 1
	movq	-24(%rbp), %rax # load token (address) in rax
	addq	%rdx, %rax      # rax = &(token[pos-1])
	movzbl	(%rax), %eax    # eax = token[pos-1]

	cmpl $82, %eax      # case '82'
	je .NODE_9
	movl $-1, %eax          # default : return -1
	jmp .RET

.NODE_9:
	jmp .END          # goto END

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
	subq	$16, %rsp
	movl	%edi, -4(%rbp)
	movq	%rsi, -16(%rbp)
	cmpl	$1, -4(%rbp)
	jg	.DO_MAIN
	movl	$1, %eax
	jmp	.END_MAIN
.DO_MAIN:
	movq	-16(%rbp), %rax
	addq	$8, %rax
	movq	(%rax), %rax
	movq	%rax, %rdi
	call	compute_fst
	movl	%eax, %esi
	movl	$.PRINTF_FMT, %edi
	movl	$0, %eax
	call	printf
	movl	$0, %eax
.END_MAIN:
	leave
	ret
	.size	main, .-main
	.ident	"GCC: (GNU) 4.9.2 20150212 (Red Hat 4.9.2-6)"
	.section	.note.GNU-stack,"",@progbits
