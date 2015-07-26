package de.gymnew.sudoku.gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import de.gymnew.sudoku.model.Sudoku;

@SuppressWarnings("serial")
public class SudokuPanel extends JPanel {

	public static final int FIELD_SIZE = 10;
	public static final int NUMBER_SIZE = 8;
	
	public static final int BLOCK_SEPARATOR_WIDTH = 3;
	
	public static final int OFFSET_SIDE = 10;
	public static final int OFFSET_TOP = 10;
	public static final int OFFSET_BOTTOM = 10;
	
	private Sudoku sudoku;
	private int selectX, selectY;
	
	public SudokuPanel() {
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if(sudoku == null) return;
		
		
	}

	public Sudoku getSudoku() {
		return sudoku;
	}

	public void setSudoku(Sudoku sudoku) {
		this.sudoku = sudoku;
	}
	
	public void setSelection(int x, int y) {
		selectX = x;
		selectY = y;
	}

}
