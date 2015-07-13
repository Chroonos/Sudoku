package de.gymnew.sudoku.model;

public class Row extends Cluster {

	public Row(int row, Sudoku sudoku){
		super();
		for(int i = 0; i<9; i++){
			fields.add(sudoku.getField(i, row));
		}
	}
}
