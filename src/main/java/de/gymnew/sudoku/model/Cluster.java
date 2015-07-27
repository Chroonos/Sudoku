package de.gymnew.sudoku.model;

import java.util.HashSet;
import java.util.Set;

public abstract class Cluster {

	protected Set<Field> fields;

	protected Cluster() {
		fields = new HashSet<Field>();
	}

	public Set<Field> getFields() {
		return new HashSet<Field>(fields);
	}

	public boolean hasValue(byte value) {
		for (Field f : fields) {
			if (f.getValue() == value)
				return true;
		}
		return false;
	}

	public byte countNotes(byte note) {
		byte out = (byte) getFieldsWithNote(note).size();
		return out;
	}

	public Set<Field> getFieldsWithNote(byte note) {
		Set<Field> out = new HashSet<Field>();
		for (Field f : fields) {
			if (f.hasNote(note))
				out.add(f);
		}
		return out;
	}

	public void removeNotes(byte note) {
		for (Field f : fields) {
			f.deleteNote(note);
		}
	}

	public boolean containsField(Field field) {
		for (Field f : fields) {
			if (f.equals(field))
				return true;
		}
		return false;
	}

	public boolean isValid() {
		for (Field f1 : fields) {
			if (f1.getValue() == 0) {
				continue;
			}
			for (Field f2 : fields) {
				if (f1.getValue() == f2.getValue()) {
					return false;
				}
			}
		}
		return true;
	}
}
