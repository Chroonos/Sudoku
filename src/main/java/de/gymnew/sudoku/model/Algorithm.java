package de.gymnew.sudoku.model;

import de.gymnew.sudoku.core.Solver;

public interface Algorithm {
	
	public Sudoku solve() throws InterruptedException;
	
	public void setSolver(Solver solver);
	
}
