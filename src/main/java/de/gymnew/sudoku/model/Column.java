package de.gymnew.sudoku.model;

public class Column extends Cluster {

	public Column(int column, Sudoku sudoku) {
		super();
		for (int i = 0; i < 9; i++) {
			Field f = sudoku.getField(column, i);
			fields.add(f);
			f.setColumn(this);
		}
	}
}
