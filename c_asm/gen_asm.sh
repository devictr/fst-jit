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
EOF

rm "$FST.sort"
