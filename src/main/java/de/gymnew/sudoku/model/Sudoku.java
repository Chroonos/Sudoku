package de.gymnew.sudoku.model;

import java.io.File;

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
	
	@Override
	public Sudoku clone() {
		Sudoku sudoku = new Sudoku();
		sudoku.load(toString());
		return null;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	private void load(String string) {
		// TODO Auto-generated method stub
	}
	
	public static Sudoku load(File file) {
		// TODO Auto-generated method stub
		return null;
	}

}
