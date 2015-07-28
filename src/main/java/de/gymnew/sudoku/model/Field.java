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
		}

		return false;
	}

	public void setValue(byte value) {
		this.value = value;
	}

	public Set<Byte> getNotes() {
		synchronized (notes) {
			return new HashSet<Byte>(notes);
		}
	}

	public int countNotes() {
		synchronized (notes) {
			return notes.size();
		}
	}

	public boolean addNote(byte b) {
		synchronized (notes) {
			return notes.add(b);
		}
	}

	public boolean addNotes(Collection<Byte> col) {
		synchronized (notes) {
			return notes.addAll(col);
		}
	}

	public boolean hasNote(byte b) {
		synchronized (notes) {
			return notes.contains(b);
		}
	}

	public boolean deleteNote(byte b) {
		synchronized (notes) {
			return notes.remove(b);
		}
	}

	public void clearNotes() {
		synchronized (notes) {
			notes.clear();
		}
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
