package assembler;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ParserTest {

	Parser p;
	
	@Before
	public void setup() {
		p = new Parser("Add.asm");
	}
	
	@Test
	public void testAdvanceSkipComments() {
		p.advance();
		assertEquals("@2", p.getCurrentCommand());
	}
	
	@Test
	public void testSimpleAdvance() {
		p.advance();
		p.advance();
		assertEquals("D=A", p.getCurrentCommand());
	}
	
	@Test
	public void testAdvanceWithNoSpaceComment() {
		p.advance();
		p.advance();
		p.advance();
		assertEquals("@3", p.getCurrentCommand());
	}
	
	@Test
	public void testAdvanceWithSpaceComment() {
		p.advance();
		p.advance();
		p.advance();
		p.advance();
		assertEquals("D=D+A;JGE", p.getCurrentCommand());
	}

	@Test
	public void testACommandType() {
		p.advance();
		assertEquals(CommandType.A_COMMAND, p.commandType());
	}
	
	@Test
	public void testCCommandType() {
		p.advance();
		p.advance();
		assertEquals(CommandType.C_COMMAND, p.commandType());
	}
	
	@Test
	public void testHasMoreCommands() {
		assertTrue(p.hasMoreCommands());
		p.advance();
		p.advance();
		p.advance();
		p.advance();
		p.advance();
		assertTrue(p.hasMoreCommands());
		p.advance();
		assertFalse(p.hasMoreCommands());
	}
	
	@Test
	public void testACommandLiteralSymbol() {
		p.advance();
		assertEquals("2", p.symbol());
	}
	
	@Test
	public void testNoJumpSingleCharDestAndComp() {
		p.advance();
		p.advance();
		assertEquals("D", p.dest());
		assertEquals("A", p.comp());
	}
	
	@Test
	public void testJumpMultiCharComp() {
		p.advance();
		p.advance();
		p.advance();
		p.advance();
		assertEquals("D", p.dest());
		assertEquals("D+A", p.comp());
		assertEquals("JGE", p.jump());
	}
	
	@Test
	public void testJumpOnly() {
		p.advance();
		p.advance();
		p.advance();
		p.advance();
		p.advance();
		p.advance();
		assertEquals("", p.dest());
		assertEquals("0", p.comp());
		assertEquals("JMP", p.jump());
	}
}
