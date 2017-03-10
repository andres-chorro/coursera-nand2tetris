package assembler;

import static org.junit.Assert.*;

import org.junit.Test;

public class CodeTest {

	@Test
	public void testComp() {
		assertEquals(Code.comp("0"),   "0101010");
		assertEquals(Code.comp("1"),   "0111111");
		assertEquals(Code.comp("-1"),  "0111010");
		assertEquals(Code.comp("D"),   "0001100");
		assertEquals(Code.comp("A"),   "0110000");
		assertEquals(Code.comp("!D"),  "0001101");
		assertEquals(Code.comp("!A"),  "0110001");
		assertEquals(Code.comp("-D"),  "0001111");
		assertEquals(Code.comp("-A"),  "0110011");
		assertEquals(Code.comp("D+1"), "0011111");
		assertEquals(Code.comp("A+1"), "0110111");
		assertEquals(Code.comp("D-1"), "0001110");
		assertEquals(Code.comp("A-1"), "0110010");
		assertEquals(Code.comp("D+A"), "0000010");
		assertEquals(Code.comp("D-A"), "0010011");
		assertEquals(Code.comp("A-D"), "0000111");
		assertEquals(Code.comp("D&A"), "0000000");
		assertEquals(Code.comp("D|A"), "0010101");
		
		assertEquals(Code.comp("M"),   "1110000");
		assertEquals(Code.comp("!M"),  "1110001");
		assertEquals(Code.comp("-M"),  "1110011");
		assertEquals(Code.comp("M+1"), "1110111");
		assertEquals(Code.comp("M-1"), "1110010");
		assertEquals(Code.comp("D+M"), "1000010");
		assertEquals(Code.comp("D-M"), "1010011");
		assertEquals(Code.comp("M-D"), "1000111");
		assertEquals(Code.comp("D&M"), "1000000");
		assertEquals(Code.comp("D|M"), "1010101");
	}
	
	@Test
	public void testDest() {
		assertEquals(Code.dest(""),    "000");
		assertEquals(Code.dest("M"),   "001");
		assertEquals(Code.dest("D"),   "010");
		assertEquals(Code.dest("MD"),  "011");
		assertEquals(Code.dest("A"),   "100");
		assertEquals(Code.dest("AM"),  "101");
		assertEquals(Code.dest("AD"),  "110");
		assertEquals(Code.dest("AMD"), "111");
	}
	
	@Test
	public void testJump() {
		assertEquals(Code.jump(""), "000");
		assertEquals(Code.jump("JGT"), "001");
		assertEquals(Code.jump("JEQ"), "010");
		assertEquals(Code.jump("JGE"), "011");
		assertEquals(Code.jump("JLT"), "100");
		assertEquals(Code.jump("JNE"), "101");
		assertEquals(Code.jump("JLE"), "110");
		assertEquals(Code.jump("JMP"), "111");
	}

}
