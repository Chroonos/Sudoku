package de.gymnew.sudoku.core;

import de.gymnew.sudoku.model.Algorithm;
import de.gymnew.sudoku.model.Sudoku;

public class Solver extends Thread {
	
	private Algorithm algorithm;
	private Sudoku sudoku;
	private Sudoku result;
	
	public Solver(Algorithm algorithm, Sudoku sudoku) {
		this.algorithm = algorithm;
		this.sudoku = sudoku;
		result = null;
	}
	
	@Override
	public void run() {
		result = algorithm.solve(sudoku);
	}

	public Sudoku getResult() {
		return result;
	}
}
