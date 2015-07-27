package de.gymnew.sudoku.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import de.gymnew.sudoku.model.Sudoku;

@SuppressWarnings("serial")
public class SudokuPanel extends JPanel {

	public static final int FIELD_SIZE = 5;
	public static final int NUMBER_SIZE = 4;

	public static final int BLOCK_SEPARATOR_WIDTH = 1;

	public static final int OFFSET_SIDE = 3;
	public static final int OFFSET_TOP = 3;
	public static final int OFFSET_BOTTOM = 3;

	private Sudoku sudoku;
	private int selectX, selectY;
	private int scale;

	public SudokuPanel() {
		scale = 10;
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (sudoku == null)
			return;

		int field_size = FIELD_SIZE * scale;
		g.setFont(new Font("Arial", Font.PLAIN, NUMBER_SIZE * scale));

		for (int block_x = 0; block_x < 3; block_x++) {
			for (int block_y = 0; block_y < 3; block_y++) {
				int base_x = OFFSET_SIDE + BLOCK_SEPARATOR_WIDTH + ((FIELD_SIZE * 3) + BLOCK_SEPARATOR_WIDTH) * block_x;
				base_x *= scale;
				int base_y = OFFSET_TOP + BLOCK_SEPARATOR_WIDTH + ((FIELD_SIZE * 3) + BLOCK_SEPARATOR_WIDTH) * block_y;
				base_y *= scale;

				g.setColor(Color.BLACK);
				g.fillRect(base_x - (BLOCK_SEPARATOR_WIDTH * scale), base_y - (BLOCK_SEPARATOR_WIDTH * scale),
						(BLOCK_SEPARATOR_WIDTH * 2 + FIELD_SIZE * 3) * scale,
						(BLOCK_SEPARATOR_WIDTH * 2 + FIELD_SIZE * 3) * scale);
				g.setColor(Color.WHITE);
				g.fillRect(base_x, base_y, field_size * 3, field_size * 3);
				g.setColor(Color.BLACK);

				for (int field_x = 0; field_x < 3; field_x++) {
					for (int field_y = 0; field_y < 3; field_y++) {
						int x = base_x + (field_size * field_x);
						int y = base_y + (field_size * field_y);
						g.drawRect(x, y, field_size, field_size);

						int value = sudoku.getField(field_x + 3 * block_x, field_y + 3 * block_y).getValue();
						if (value != 0)
							g.drawChars(new char[] { ("" + value).charAt(0) }, 0, 1, x + scale, y + field_size - scale);
					}
				}
			}
		}
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
