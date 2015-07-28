package de.gymnew.sudoku.model;

public class Row extends Cluster {

	public Row(int row, Sudoku sudoku) {
		super();
		for (int i = 0; i < 9; i++) {
			Field f = sudoku.getField(i, row);
			fields.add(f);
			f.setRow(this);
		}
	}
}
