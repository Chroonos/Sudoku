package de.gymnew.sudoku.model;

import java.util.Set;

public abstract class Cluster {

	private Set<Field> fields;

	public Cluster(Set<Field> fields) {
		this.fields = fields;
	}

	public Set<Field> getFields() {
		return fields;
	}

	public boolean hasValue(byte value) {
		for (Field f : fields) {
			if (f.getValue() == value)
				return true;
		}
		return false;
	}

	public byte countNotes(byte note) {
		byte count = 0;
		for (Field f : fields) {
			if (f.getNotes() == note)
				count++;
		}
		return count;
	}

}
