package de.gymnew.sudoku.model;

import java.util.HashSet;
import java.util.Set;

public abstract class Cluster {

	protected Set<Field> fields;
	
	protected Cluster() {
		fields = new HashSet<Field>();
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
			if (f.hasNote(note))
				count++;
		}
		return count;
	}

}
