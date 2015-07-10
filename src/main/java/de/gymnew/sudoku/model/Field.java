package de.gymnew.sudoku.model;

import java.util.Set;

public class Field {

	private byte value; // 0 = undefined
	private Set<Byte> notes;
	private Row row;
	private Column col;
	private Block block;
	
	public byte getValue() {
		return value;
	}
	public void setValue(byte value) {
		this.value = value;
	}
	
}
