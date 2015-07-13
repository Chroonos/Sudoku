package de.gymnew.sudoku.model;

public class Block extends Cluster {

	public Block(int x, int y, Sudoku sudoku) {
		super();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Field f = sudoku.getField(x + i, y + j);
				fields.add(f);
				f.setBlock(this);
			}
		}
	}
}
