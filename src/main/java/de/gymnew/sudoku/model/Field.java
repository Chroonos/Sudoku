package de.gymnew.sudoku.model;

import java.util.Collection;
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
	
	public boolean canHaveValue(byte b) {
		if(row.hasValue(b) == false && col.hasValue(b) == false && block.hasValue(b) == false) {
			return true;
		}
		else return false;
	}

	public void setValue(byte value) {
		this.value = value;
	}

	public Set<Byte> getNotes() {
		return notes;
	}

	public boolean addNote(byte b) {
		return notes.add(b);
	}
	
	public boolean addNotes(Collection<Byte> col) {
		return notes.addAll(col);
	}

	public boolean hasNote(byte b) {
		return notes.contains(b);
	}

	public boolean deleteNote(byte b) {
		return notes.remove(b);
	}
	
	public void clearNotes() {
		notes.clear();
	}

	public Row getRow() {
		return row;
	}

	public Column getColumn() {
		return col;
	}

	public Block getBlock() {
		return block;
	}

	void setRow(Row row) {
		this.row = row;
	}

	void setBlock(Block block) {
		this.block = block;
	}

	void setColumn(Column column) {
		this.col = column;
	}
}
