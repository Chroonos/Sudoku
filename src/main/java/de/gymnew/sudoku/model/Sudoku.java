package de.gymnew.sudoku.model;

public class Sudoku {

	private Field[][] fields;

	public Sudoku() {
		fields = new Field[9][9];
		
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				fields[i][j] = new Field();
			}
		}
	}

	public Field getField(int column, int row) {
		return fields[column][row];
	}

}
