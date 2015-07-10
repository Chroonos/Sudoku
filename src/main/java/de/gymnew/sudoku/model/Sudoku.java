package de.gymnew.sudoku.model;

public class Sudoku {

	private Field[][] fields;

	public Field getField(int column, int row) {
		return fields[column][row];
	}

}
