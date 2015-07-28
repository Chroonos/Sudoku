package de.gymnew.sudoku.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Set;

import javax.swing.JPanel;

import de.gymnew.sudoku.model.Sudoku;

@SuppressWarnings("serial")
public class SudokuPanel extends JPanel {

	public static final int FIELD_SIZE = 10;
	public static final int NUMBER_SIZE = 8;
	public static final int NUMBER_OFFSET = 2;
	public static final int NOTE_SIZE = 2;
	public static final int NOTE_OFFSET = 2;

	public static final int BLOCK_SEPARATOR_WIDTH = 2;

	public static final int OFFSET_SIDE = 6;
	public static final int OFFSET_TOP = 6;
	public static final int OFFSET_BOTTOM = 6;

	private Sudoku sudoku;
	private MainFrame frame;

	public SudokuPanel(MainFrame frame) {
		this.frame = frame;
		int width = (OFFSET_SIDE * 2 + BLOCK_SEPARATOR_WIDTH * 4 + FIELD_SIZE * 9) * frame.getScale();
		int length = (OFFSET_TOP * 2 + OFFSET_BOTTOM * 2 + BLOCK_SEPARATOR_WIDTH * 4 + FIELD_SIZE * 9)
				* frame.getScale();
		frame.setSize(width, length);
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (sudoku == null) {
			sudoku = new Sudoku();
		}

		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		int field_size = FIELD_SIZE * frame.getScale();
		int number_offset = NUMBER_OFFSET * frame.getScale();
		g.setFont(new Font("Arial", Font.PLAIN, NUMBER_SIZE * frame.getScale()));

		for (int block_x = 0; block_x < 3; block_x++) {
			for (int block_y = 0; block_y < 3; block_y++) {
				int base_x = OFFSET_SIDE + BLOCK_SEPARATOR_WIDTH + ((FIELD_SIZE * 3) + BLOCK_SEPARATOR_WIDTH) * block_x;
				base_x *= frame.getScale();
				int base_y = OFFSET_TOP + BLOCK_SEPARATOR_WIDTH + ((FIELD_SIZE * 3) + BLOCK_SEPARATOR_WIDTH) * block_y;
				base_y *= frame.getScale();

				g.setColor(Color.BLACK);
				g.fillRect(base_x - (BLOCK_SEPARATOR_WIDTH * frame.getScale()),
						base_y - (BLOCK_SEPARATOR_WIDTH * frame.getScale()),
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
						// Numbers
						int value = sudoku.getField(field_x + 3 * block_x, field_y + 3 * block_y).getValue();
						if (value != 0) {
							if (sudoku.getField(field_x + 3 * block_x, field_y + 3 * block_y).isLocked())
								g.setColor(Color.GRAY);
							String s = "" + value;
							g.drawString(s, x + number_offset, y + field_size - number_offset);
							g.setColor(Color.BLACK);
						} else { // Notes
							int note_offset = NOTE_OFFSET * frame.getScale();
							Set<Byte> notes = sudoku.getField(field_x + 3 * block_x, field_y + 3 * block_y).getNotes();

							// TODO remove if following method is better
							// String s = "";
							// for (byte f : notes) {
							// s = s + f + " ";
							// }
							// String s1 = "";
							// String s2 = "";
							// String s3 = "";
							// s1 = s.substring(0);
							// if (s1.length() > 6) {
							// s1 = s1.substring(0, 5);
							// s2 = s.substring(6);
							// if (s2.length() > 6) {
							// s2 = s2.substring(0, 5);
							// s3 = s.substring(12);
							// }
							// }

							// String s1 = "";
							// String s2 = "";
							// String s3 = "";
							//
							// for (byte f : notes) {
							// for (int i = 1; i < 10; i++) {
							// if (i < 4 && i == f)
							// s1 = s1 + i + " ";
							// if (i > 3 && i < 7 && i == f)
							// s2 = s2 + i + " ";
							// if (i > 6 && i < 10 && i == f)
							// s3 = s3 + i + " ";
							// }
							// } // TODO one unique position for every note?
							g.setFont(new Font("Arial", Font.PLAIN, NOTE_SIZE * frame.getScale()));
							String s = "";
							for (byte f : notes) {
								for (int i = 1; i < 10; i++) {
									if (i < 4 && i == f) {
										int ys = y + field_size - note_offset * 3;
										if (i == 1)
											g.drawString("1", x + note_offset, ys);
										if (i == 2)
											g.drawString("2", x + note_offset * 2, ys);
										if (i == 3)
											g.drawString("3", x + note_offset * 3, ys);
									}
									if (i > 3 && i < 7 && i == f){
										int ys = y + field_size - note_offset * 2;
										if (i == 4)
											g.drawString("4", x + note_offset, ys);
										if (i == 5)
											g.drawString("5", x + note_offset * 2, ys);
										if (i == 6)
											g.drawString("6", x + note_offset * 3, ys);
									}
									if (i > 6 && i < 10 && i == f){
										int ys = y + field_size - note_offset;
										if (i == 7)
											g.drawString("7", x + note_offset, ys);
										if (i == 8)
											g.drawString("8", x + note_offset * 2, ys);
										if (i == 9)
											g.drawString("9", x + note_offset * 3, ys);
									}
								}
							}

							
							// g.drawString(s1, x + note_offset, y + field_size
							// - note_offset * 3);
							// g.drawString(s2, x + note_offset, y + field_size
							// - note_offset * 2);
							// g.drawString(s3, x + note_offset, y + field_size
							// - note_offset);
							g.setFont(new Font("Arial", Font.PLAIN, NUMBER_SIZE * frame.getScale()));

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
