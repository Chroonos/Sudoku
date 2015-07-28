package de.gymnew.sudoku.core;

import de.gymnew.sudoku.model.Sudoku;

public interface SolverWatcher {

	public void onUpdate(Solver solver, Sudoku sudoku);

	public void onFinished(Solver solver);

	public void onInterrupted(Solver solver);

}
