package assembler;

import java.io.FileNotFoundException;
import java.io.FileReader;



public class Parser {
	private FileReader reader;
	private String currentCommand;
	
	public Parser(String inputFileName) throws FileNotFoundException {
		reader = new FileReader(inputFileName);
	}
	
	public boolean hasMoreCommands() {
		//TODO
		return false;
	}
	
	public void advance() {
		//TODO
	}
	
	public CommandType commandType() {
		//TODO
		return null;
	}
	
	public String symbol() {
		//TODO
		return null;
	}
	
	public String dest() {
		//TODO
		return null;
	}
	
	public String comp() {
		//TODO
		return null;
	}
	
	public String jump() {
		//TODO
		return null;
	}
	
}

