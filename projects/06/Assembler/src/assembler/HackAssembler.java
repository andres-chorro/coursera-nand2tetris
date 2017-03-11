package assembler;

import java.io.FileWriter;
import java.io.IOException;

public class HackAssembler {
	
	private SymbolTable symbolTable;
	private Parser p;
	FileWriter output;
	
	public static void main(String[] args) {
		System.out.println("Starting HackAssembler.java");
		if (args.length < 2) {
			System.out.println("Usage: $ HackAssembler <.asm FileName>");
			System.exit(1);
		}
		
		HackAssembler h = new HackAssembler(args[1]);
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
	
	public void assemble() {
		
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
