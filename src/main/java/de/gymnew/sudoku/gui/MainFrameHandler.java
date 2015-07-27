package de.gymnew.sudoku.gui;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import de.gymnew.sudoku.algorithm.Standard;
import de.gymnew.sudoku.core.Solver;
import de.gymnew.sudoku.core.SolverWatcher;
import de.gymnew.sudoku.model.Field;
import de.gymnew.sudoku.model.Sudoku;
import static de.gymnew.sudoku.gui.SudokuPanel.*;

public class MainFrameHandler extends MouseAdapter implements SolverWatcher{

	private MainFrame frame;

	public MainFrameHandler(MainFrame frame) {
		this.frame = frame;
	}

	/* ================================================== */
	// From DrawPanel
	/* ================================================== */

	@Override
	public void mouseClicked(MouseEvent event) {
		Point click = event.getPoint();
		int x = (int) click.getX();
		int y = (int) click.getY();
		x -= (OFFSET_SIDE + BLOCK_SEPARATOR_WIDTH) * frame.getScale();
		y -= (OFFSET_TOP + BLOCK_SEPARATOR_WIDTH) * frame.getScale();
		if (x <= 0 || y <= 0)
			return;
		int block_x = (int) x / ((FIELD_SIZE * 3 + BLOCK_SEPARATOR_WIDTH) * frame.getScale());
		int block_y = (int) y / ((FIELD_SIZE * 3 + BLOCK_SEPARATOR_WIDTH) * frame.getScale());
		if (block_x > 2 || block_y > 2)
			return;
		x -= block_x * (FIELD_SIZE * 3 + BLOCK_SEPARATOR_WIDTH) * frame.getScale();
		y -= block_y * (FIELD_SIZE * 3 + BLOCK_SEPARATOR_WIDTH) * frame.getScale();
		int field_x = (int) x / (FIELD_SIZE * frame.getScale());
		int field_y = (int) y / (FIELD_SIZE * frame.getScale());
		if (field_x > 2 || field_y > 2)
			return;

		Field field = frame.getSudoku().getField(block_x * 3 + field_x, block_y * 3 + field_y);

		if (event.getButton() == MouseEvent.BUTTON2) {
			field.setLocked((!field.isLocked()) && field.getValue() != 0);
			frame.repaint();
			return;
		}

		if (field.isLocked())
			return;

		if (event.getButton() == MouseEvent.BUTTON1) {
			String s = JOptionPane.showInputDialog(frame, "Wert: (0 = leer)", field.getValue());
			byte b;
			try {
				b = Byte.parseByte(s);
			} catch (NumberFormatException e) {
				b = -1;
			}
			if (b > -1 && b < 10)
				field.setValue(b);
		} else if (event.getButton() == MouseEvent.BUTTON3) {
			Set<Byte> notes = field.getNotes();
			String n = "";
			for (byte f : notes) {
				n = n + f;
			}
			String s = JOptionPane.showInputDialog(frame, "Notizen:", n);
			if (s == null)
				return;

			Set<Byte> newNotes = new HashSet<Byte>();
			for (char c : s.toCharArray()) {
				try {
					byte b = Byte.parseByte("" + c);
					if (b != 0)
						newNotes.add(b);
				} catch (NumberFormatException e) {
					return; // TODO error
				}
			}
			field.clearNotes();
			field.addNotes(newNotes);
		}
		frame.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	/* ================================================== */
	// From MainFrame
	/* ================================================== */

	public void onMenuQuit() {
		System.exit(0);

	}

	public void onMenuLoad() {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File file = chooser.getSelectedFile();

		if (file != null) {
			try {
				frame.setSudoku(Sudoku.load(file));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Keine Datei ausgew\u00E4hlt");
		}
	}

	public void onMenuSave() {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File file = chooser.getSelectedFile();

		if (file != null) {
			try {
				frame.getSudoku().save(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Kein Speicherort ausgew\u00E4hlt");
		}

	}

	public void onMenuCredits() {
		JOptionPane.showMessageDialog(frame,
				"Tobias Bodensteiner, Sven Gebauer, Tobias Gr\u00F6mer, Katharina Hauer, Valentin Kellner, Elena Menzl, Jonas Piehler, Alexander Puff, Maximilian Rauch, Catrin Schnupfhagn, Matthias Zetzl");

	}

	public void onMenuSolve() {
		if(frame.getSolver() != null && frame.getSolver().isAlive()){
			JOptionPane.showMessageDialog(frame, "Solver l&auml;uft bereits");
			return;
		}
		frame.setSolver(new Solver(new Standard(frame.getSudoku(), false), this));
		frame.getSolver().start();
	}

	/* ================================================== */
	// SolverWatcher methods
	/* ================================================== */
	
	@Override
	public void onUpdate(Solver solver, Sudoku sudoku) {
		frame.setSudoku(sudoku);
		frame.getContentPane().repaint();
	}

	@Override
	public void onFinised(Solver solver) {
		frame.setSudoku(solver.getResult());
		frame.getContentPane().repaint();
		JOptionPane.showMessageDialog(frame, "Der Solver hat ein Ergebnis gefunden!");
	}
}
