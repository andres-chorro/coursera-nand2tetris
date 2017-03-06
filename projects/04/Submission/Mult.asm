// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)

// i = 0; R2=0
	@i
	M=0
	@R2
	M=0

(LOOP)
	// if (R1-i <= 0) END
	@R1
	D=M
	@i
	D=D-M
	@END
	D;JLE

	// R2 += R0; i++
	@R0
	D=M
	@R2
	M=M+D
	@i
	M=M+1
	@LOOP
	0;JMP

(END)
	@END
	0;JMP


