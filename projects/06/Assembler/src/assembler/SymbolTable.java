package assembler;

import java.util.HashMap;

public class SymbolTable {
	private HashMap<String, Integer> table;
	public SymbolTable() {
		table = new HashMap<>();
	}
	
	public void addEntry(String symbol, int address) {
		table.put(symbol, address);
	}
	
	public boolean contains(String symbol) {
		return table.containsKey(symbol);
	}
	
	public int getAddress(String symbol) {
		return table.get(symbol);
	}
	
}

