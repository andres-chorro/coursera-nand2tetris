package assembler;

import java.io.FileWriter;
import java.io.IOException;

public class HackAssembler {

	private SymbolTable symbolTable;
	private Parser p;
	FileWriter output;

	public static void main(String[] args) throws IOException {
		System.out.println("Starting HackAssembler.java");
		if (args.length != 1) {
			System.out.println("Usage: $ HackAssembler <.asm FileName>");
			System.exit(1);
		}

		HackAssembler h = new HackAssembler(args[0]);
		h.assemble();
	}

	public HackAssembler(String inputFile) {
		symbolTable = new SymbolTable();
		String outputFileName = inputFile.substring(0, inputFile.length() - 3);
		outputFileName += "hack";
		p = new Parser(inputFile);
		try {
			output = new FileWriter(outputFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void assemble() throws IOException {
		populateSymbolTable();
		writeCommands();
		output.close();
		p.close();
	}

	private void populateSymbolTable() {
		addPredefinedSymbols();
		
		int lineNumber = 0;
		while (p.hasMoreCommands()) {
			p.advance();
			if (currentCommandIsLabel()) {
				symbolTable.addEntry(p.symbol(), lineNumber);
			} else {
				lineNumber++;
			}		
		}
		p.reset();
		
		int nextOpenAddress = 16;
		while (p.hasMoreCommands()) {
			p.advance();
			if (currentCommandIsASymbol() && 
					!symbolTable.contains(p.symbol())) {
				symbolTable.addEntry(p.symbol(), nextOpenAddress);
				nextOpenAddress++;
			}
		}
		p.reset();
	}
	
	private void writeCommands() throws IOException {
		while (p.hasMoreCommands()) {
			p.advance();
			if (currentCommandIsLabel()) {
				continue;
			}
			else if (p.commandType().equals(CommandType.A_COMMAND)) {
				output.write(translateACommand());
				output.write("\n");
			} else if (p.commandType().equals(CommandType.C_COMMAND)) {
				output.write(translateCCommand());
				output.write("\n");
			}
		}
	}
	
	private String translateACommand() {
		int address;
		if (isInteger(p.symbol())) {
			address = Integer.parseInt(p.symbol());
		} else {
			address = symbolTable.getAddress(p.symbol());
		}
		return "0" + intToBinaryString(address, 15);
	}
	
	private String translateCCommand() {
		String result = "111";
		result += Code.comp(p.comp());
		result += Code.dest(p.dest());
		result += Code.jump(p.jump());
		return result;
	}
	
	private String intToBinaryString(int n, int numBits) {
		String result = Integer.toBinaryString(n);
		if (result.length() > numBits)
			result = result.substring(result.length() - numBits);
		else {
			while (result.length() < numBits) {
				result = "0" + result;
			}
		}
		return result;
	}
	
	private boolean currentCommandIsLabel() {
		return p.commandType().equals(CommandType.L_COMMAND);
	}

	private boolean currentCommandIsASymbol() {
		return p.commandType().equals(CommandType.A_COMMAND) 
						&& !isInteger(p.symbol());
	}
	
	private boolean isInteger(String str) {
		return str.matches("\\d+");
	}

	private void addPredefinedSymbols() {
		symbolTable.addEntry("SP", 0);
		symbolTable.addEntry("LCL", 1);
		symbolTable.addEntry("ARG", 2);
		symbolTable.addEntry("THIS", 3);
		symbolTable.addEntry("THAT", 4);
		symbolTable.addEntry("R0", 0);
		symbolTable.addEntry("R1", 1);
		symbolTable.addEntry("R2", 2);
		symbolTable.addEntry("R3", 3);
		symbolTable.addEntry("R4", 4);
		symbolTable.addEntry("R5", 5);
		symbolTable.addEntry("R6", 6);
		symbolTable.addEntry("R7", 7);
		symbolTable.addEntry("R8", 8);
		symbolTable.addEntry("R9", 9);
		symbolTable.addEntry("R10", 10);
		symbolTable.addEntry("R11", 11);
		symbolTable.addEntry("R12", 12);
		symbolTable.addEntry("R13", 13);
		symbolTable.addEntry("R14", 14);
		symbolTable.addEntry("R15", 15);
		symbolTable.addEntry("SCREEN", 16384);
		symbolTable.addEntry("KBD", 24576);
	}
}
