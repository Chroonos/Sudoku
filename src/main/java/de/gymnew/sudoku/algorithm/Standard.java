package de.gymnew.sudoku.algorithm;

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
	
	public Standard(Sudoku sudoku, boolean rebuildNotes) {
		this.sudoku = sudoku;
		this.rebuildNotes = rebuildNotes;
	}
	
	@Override
	public Sudoku solve() {
		if(rebuildNotes) createNotes();
		
		do {
			madeChanges = false;
			
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
	
	private void tryRandom() {
		Sudoku clone = sudoku.clone();
		
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
}
