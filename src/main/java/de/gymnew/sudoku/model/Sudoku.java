package de.gymnew.sudoku.model;

public class Sudoku {

	private Field[][] fields;

	public Sudoku() {
		fields = new Field[9][9];
	}

	public Field getField(int column, int row) {
		return fields[column][row];
	}

}
