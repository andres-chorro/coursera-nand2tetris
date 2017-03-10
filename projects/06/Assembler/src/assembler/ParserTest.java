package assembler;

import static org.junit.Assert.*;

import org.junit.AfterClass;
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
		assertEquals("D=D+A", p.getCurrentCommand());
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
}
