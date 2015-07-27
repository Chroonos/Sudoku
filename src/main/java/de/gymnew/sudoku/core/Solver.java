package de.gymnew.sudoku.core;

import de.gymnew.sudoku.model.Algorithm;
import de.gymnew.sudoku.model.Sudoku;

public class Solver extends Thread {
	
	private Algorithm algorithm;
	private Sudoku result;
	private SolverWatcher watcher;
	
	public Solver(Algorithm algorithm, SolverWatcher watcher) {
		this.algorithm = algorithm;
		result = null;
		this.watcher = watcher;
		algorithm.setSolver(this);
	}
	
	@Override
	public void run() {
		try {
			result = algorithm.solve();
		} catch(InterruptedException e) {
			// TODO Notify SolverWatcher
		}
	}

	public Sudoku getResult() {
		return result;
	}
	
	public SolverWatcher getWatcher() {
		return watcher;
	}
}
