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
	
	private MainFrame frame;

	public SudokuPanel(MainFrame frame) {
		this.frame = frame;
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (sudoku == null)
			return;

		int field_size = FIELD_SIZE * frame.getScale();
		g.setFont(new Font("Arial", Font.PLAIN, NUMBER_SIZE * frame.getScale()));

		for (int block_x = 0; block_x < 3; block_x++) {
			for (int block_y = 0; block_y < 3; block_y++) {
				int base_x = OFFSET_SIDE + BLOCK_SEPARATOR_WIDTH + ((FIELD_SIZE * 3) + BLOCK_SEPARATOR_WIDTH) * block_x;
				base_x *= frame.getScale();
				int base_y = OFFSET_TOP + BLOCK_SEPARATOR_WIDTH + ((FIELD_SIZE * 3) + BLOCK_SEPARATOR_WIDTH) * block_y;
				base_y *= frame.getScale();

				g.setColor(Color.BLACK);
				g.fillRect(base_x - (BLOCK_SEPARATOR_WIDTH * frame.getScale()), base_y - (BLOCK_SEPARATOR_WIDTH * frame.getScale()),
						(BLOCK_SEPARATOR_WIDTH * 2 + FIELD_SIZE * 3) * frame.getScale(),
						(BLOCK_SEPARATOR_WIDTH * 2 + FIELD_SIZE * 3) * frame.getScale());
				g.setColor(Color.WHITE);
				g.fillRect(base_x, base_y, field_size * 3, field_size * 3);
				g.setColor(Color.BLACK);

				for (int field_x = 0; field_x < 3; field_x++) {
					for (int field_y = 0; field_y < 3; field_y++) {
						int x = base_x + (field_size * field_x);
						int y = base_y + (field_size * field_y);
						g.drawRect(x, y, field_size, field_size);

						int value = sudoku.getField(field_x + 3 * block_x, field_y + 3 * block_y).getValue();
						if (value != 0) {
							if(sudoku.getField(field_x + 3 * block_x, field_y + 3 * block_y).isLocked())
								g.setColor(Color.GRAY);
							g.drawChars(new char[] { ("" + value).charAt(0) }, 0, 1, x + frame.getScale(), y + field_size - frame.getScale());
							g.setColor(Color.BLACK);
						}
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

}
