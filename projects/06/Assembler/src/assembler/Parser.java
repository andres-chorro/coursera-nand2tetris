package assembler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Parser {
	private Scanner sc;
	private ArrayList<String> tokens;
	private int currentIndex;
	private String currentCommand;

	public Parser(String inputFileName) {
		try {
			sc = new Scanner(new FileReader(inputFileName));
			currentIndex = -1;
			tokens = new ArrayList<>();
			tokenize();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
	private void tokenize() {
		while (sc.hasNext()) {
			String curr = sc.next();
			if (curr.startsWith("//")) {
				sc.nextLine();
			}
			else {
				tokens.add(curr);
			}
		}
	}

	public boolean hasMoreCommands() {
		return currentIndex < tokens.size() - 1;
	}

	public void advance() {
		currentIndex++;
		updateCurrentCommand();
	}

	public CommandType commandType() {
		if (currentCommand.startsWith("("))
			return CommandType.L_COMMAND;
		else if (currentCommand.startsWith("@"))
			return CommandType.A_COMMAND;
		else
			return CommandType.C_COMMAND;
	}

	private void updateCurrentCommand() {
		currentCommand = tokens.get(currentIndex);
	}
	
	public String getCurrentCommand() {
		return currentCommand;
	}

	public List<String> getTokens() {
		return tokens;
	}
	
	public String symbol() {
		String result = "";
		if (currentCommand.startsWith("@")) {
			result = currentCommand.substring(1);
		} else if (currentCommand.startsWith("(")) {
			result = currentCommand.substring(1, currentCommand.length()-1);
		}
		return result;
	}

	public String dest() {
		int equalsIndex = currentCommand.indexOf("=");
		if (equalsIndex == -1) {
			return "";
		}
		return currentCommand.substring(0, equalsIndex);
	}

	public String comp() {
		int equalsIndex = currentCommand.indexOf("=");
		int semicolonIndex = currentCommand.indexOf(";");
		if (semicolonIndex == -1) {
			semicolonIndex = currentCommand.length();
		}
		return currentCommand.substring(equalsIndex + 1, semicolonIndex);
	}

	public String jump() {
		int semicolonIndex = currentCommand.indexOf(";");
		if (semicolonIndex == -1) {
			return "";
		}
		else {
			return currentCommand.substring(semicolonIndex + 1, currentCommand.length());
		}
	}
	
	public void reset() {
		currentIndex = -1;
	}

	public void close() {
		sc.close();
	}
	

}
