// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.

//lastIndex = 8192
@8192
D=A
@last
M=D

(LOOP)
	//i=0 if KBD==0 goto EMPTY
	@i
	M=0
	@KBD
	D=M
	@EMPTY
	D;JEQ

(FILL)
	//RAM[SCREEN + i] = -1
	@i
	D=M
	@SCREEN
	A=A+D
	M=-1

	//i++
	@i
	M=M+1
	D=M

	//if i < lastIndex, goto FILL
	@last
	D=M-D
	@FILL
	D;JGT

//goto LOOP
@LOOP
0;JMP

(EMPTY)
//RAM[SCREEN + i] = 0
	@i
	D=M
	@SCREEN
	A=A+D
	M=0

	//i++
	@i
	M=M+1
	D=M

	//if i < lastIndex, goto EMPTY
	@last
	D=M-D
	@EMPTY
	D;JGT

//goto LOOP
@LOOP
0;JMP