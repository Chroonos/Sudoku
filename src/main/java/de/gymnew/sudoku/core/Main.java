package de.gymnew.sudoku.core;

import de.gymnew.sudoku.gui.MainFrame;
import de.gymnew.sudoku.model.Sudoku;

public class Main {

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setSudoku(new Sudoku());
		frame.setVisible(true);
		frame.getContentPane().repaint();
	}

}
