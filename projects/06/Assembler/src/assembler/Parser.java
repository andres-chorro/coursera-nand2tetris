package assembler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;



public class Parser {
	private Scanner sc;
	private String currentCommand;
	private CommandType currentCommandType;
	
	public Parser(String inputFileName) {
		try {
			sc = new Scanner(new FileReader(inputFileName));
			//sc.useDelimiter("//.*\\n|\\s");
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
	public boolean hasMoreCommands() {
		return sc.hasNext();
	}
	
	public void advance() {
		boolean commandFound = false;
		while (!commandFound) {
			currentCommand = sc.next();
			if (currentCommand.startsWith("//"))
				sc.nextLine();
			else
				commandFound = true;
		}
	}
	
	public CommandType commandType() {
		if (currentCommand.startsWith("("))
			return CommandType.L_COMMAND;
		else if (currentCommand.startsWith("@"))
			return CommandType.A_COMMAND;
		else
			return CommandType.C_COMMAND;
	}
	
	public String getCurrentCommand() {
		return currentCommand;
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
	
	public void close() {
		sc.close();
	}
	
}

