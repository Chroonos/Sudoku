package de.gymnew.sudoku.algorithm;

import de.gymnew.sudoku.core.Solver;
import de.gymnew.sudoku.model.Algorithm;
import de.gymnew.sudoku.model.Block;
import de.gymnew.sudoku.model.Column;
import de.gymnew.sudoku.model.Field;
import de.gymnew.sudoku.model.Row;
import de.gymnew.sudoku.model.Sudoku;

public class Standard implements Algorithm {
	
	private Sudoku sudoku;
	private boolean rebuildNotes;
	private boolean madeChanges;
	private Solver solver;
	
	public Standard(Sudoku sudoku, boolean rebuildNotes) {
		this.sudoku = sudoku;
		this.rebuildNotes = rebuildNotes;
	}

	public void setSolver(Solver solver) {
		this.solver = solver;
	}
	
	public Sudoku getSudoku() {
		return sudoku;
	}
	
	@Override
	public Sudoku solve() throws InterruptedException{
		if(rebuildNotes) createNotes();
		
		do {
			madeChanges = false;
			if(isSolved()) return sudoku;
			if(Thread.interrupted()) throw new InterruptedException();
			solver.getWatcher().onUpdate(solver, sudoku);
			
			singleNoteToValue();
			if(madeChanges) continue;
			
			singleNoteInClusterToValue();
			if(madeChanges) continue;
			
		} while(madeChanges);
		
		tryRandom();
		if(madeChanges) return sudoku;
		else return null;
	}
	
	/*==================================================*/
	// Non-Deterministic algorithm parts
	/*==================================================*/
	
	private void tryRandom() throws InterruptedException {
		Sudoku clone = sudoku.clone();
		for (int k = 2; k < 10; k++) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (sudoku.getField(i, j).getNotes().size() == k) {
						Byte[] notes = sudoku.getField(i, j).getNotes()
								.toArray(new Byte[k]);
						int l = 0;
						while (l < k + 1) {
							byte b = notes[l];
							sudoku = clone;
							sudoku.getField(i, j).setValue(b);
							if (solve() == null) {
								l++;
							} else {
								madeChanges = true;
								return;
							}
						}
					}
				}
			}
		}
	}
	
	/*==================================================*/
	// Deterministic algorithm parts
	/*==================================================*/
	
	private void createNotes() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9;j++) {
				Field field = sudoku.getField(i, j);
				field.clearNotes();
				
				for (byte k = 1; k < 10; k++) {
					if (field.getValue() == 0 && field.canHaveValue(k)) {
							field.addNote(k);
					}
				}
			}
		}
	}
	
	private void singleNoteToValue() {
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++) {
				Field field = sudoku.getField(i, j);
				if (field.getNotes().size() == 1) {
					byte b = field.getNotes().toArray(new Byte[1])[0];
					setValue(field, b);
				}
			}
		}
	}
	
	private void singleNoteInClusterToValue(){
		for(Column c : sudoku.getColumns()){
			for(int i = 1; i < 10; i++){
				if(c.countNotes((byte) i) == 1){
					for(Field f : c.getFieldsWithNote((byte) i)){
						setValue(f, (byte) i);
					}
				}
			}
		}
		
		for(Row r : sudoku.getRows()){
			for(int i = 1; i < 10; i++){
				if(r.countNotes((byte) i) == 1){
					for(Field f : r.getFieldsWithNote((byte) i)){
						setValue(f, (byte) i);
					}
				}
			}
		}
		
		for(Block[] blocks : sudoku.getBlocks()){
			for(Block b : blocks){
				for(int i = 1; i < 10; i++){
					if(b.countNotes((byte) i) == 1){
						for(Field f : b.getFieldsWithNote((byte) i)){
							setValue(f, (byte) i);
						}
					}
				}
			}
		}
	}
	
	/*==================================================*/
	// Auxiliary methods
	/*==================================================*/
	
	private void setValue(Field field, byte value) {
		field.clearNotes();
		field.setValue(value);
		
		field.getRow().removeNotes(value);
		field.getColumn().removeNotes(value);
		field.getBlock().removeNotes(value);
		
		madeChanges = true;
	}
	
	private boolean isSolved() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(sudoku.getField(i, j).getValue() == 0)
					return false;
			}
		}
		return true;
	}
}
