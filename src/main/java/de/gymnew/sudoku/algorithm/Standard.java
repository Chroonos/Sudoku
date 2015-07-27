package de.gymnew.sudoku.algorithm;

import de.gymnew.sudoku.model.Algorithm;
import de.gymnew.sudoku.model.Sudoku;

public class Standard implements Algorithm {

	@Override
	public Sudoku solve(Sudoku sudoku) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void createNotes(Sudoku sudoku) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9;j++) {
				for (byte k = 1; k < 10; k++) {
					if (sudoku.getField(i,j).getValue() == 0) {
						if (sudoku.getField(i, j).canHaveValue(k) == true && sudoku.getField(i, j).hasNote(k) == false) {
							sudoku.getField(i, j).addNote(k);
						}
					}
					else { }
				}
			}
		}
	}
	
	private void singleNoteToValue(Sudoku sudoku) {
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++) {
				if (sudoku.getField(i, j).getNotes().size() == 1) {
					byte b = sudoku.getField(i, j).getNotes().toArray(new Byte[1])[0];
					sudoku.getField(i, j).setValue(b);
					sudoku.getField(i, j).clearNotes();
				}
			}
		}

	}
	
}
