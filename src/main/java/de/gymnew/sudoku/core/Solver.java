package de.gymnew.sudoku.core;

import de.gymnew.sudoku.model.Algorithm;
import de.gymnew.sudoku.model.Sudoku;

public class Solver extends Thread {
	
	private Algorithm algorithm;
	private Sudoku result;
	
	public Solver(Algorithm algorithm) {
		this.algorithm = algorithm;
		result = null;
	}
	
	@Override
	public void run() {
		result = algorithm.solve();
	}

	public Sudoku getResult() {
		return result;
	}
}
