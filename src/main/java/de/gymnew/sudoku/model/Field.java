package de.gymnew.sudoku.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Field {

	private byte value; // 0 = undefined
	private boolean locked;
	private Set<Byte> notes;
	private Row row;
	private Column col;
	private Block block;

	Field() {
		notes = new HashSet<Byte>();
	}

	public byte getValue() {
		return value;
	}

	public boolean canHaveValue(byte b) {
		if (b == 0) {
			return true;
		}

		if (row.hasValue(b) == false && col.hasValue(b) == false && block.hasValue(b) == false) {
			return true;
		} else
			return false;
	}

	public void setValue(byte value) {
		this.value = value;
	}

	public Set<Byte> getNotes() {
		return notes;
	}

	public synchronized int countNotes() {
		return notes.size();
	}

	public synchronized boolean addNote(byte b) {
		return notes.add(b);
	}

	public synchronized boolean addNotes(Collection<Byte> col) {
		return notes.addAll(col);
	}

	public synchronized boolean hasNote(byte b) {
		return notes.contains(b);
	}

	public synchronized boolean deleteNote(byte b) {
		return notes.remove(b);
	}

	public synchronized void clearNotes() {
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

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}
