package de.gymnew.sudoku.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import de.gymnew.sudoku.model.Sudoku;

public class MainFrameHandler implements MouseListener {

	private MainFrame frame;

	public MainFrameHandler(MainFrame frame) {
		this.frame = frame;
	}

	/* ================================================== */
	// From DrawPanel
	/* ================================================== */

	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub

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
			frame.setSudoku(Sudoku.load(file));
		}
		else{
			JOptionPane.showMessageDialog(frame, "Keine Datei ausgewählt");
		}
	}

	public void onMenuSave() {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File file = chooser.getSelectedFile();

		if (file != null) {
			frame.getSudoku().save(file);
		}
		else{
			JOptionPane.showMessageDialog(frame, "Kein Speicherort ausgewählt");
		}

	}

	public void onMenuStartSolver() {
		// TODO Auto-generated method stub

	}

	public void onMenuCredits() {
		JOptionPane.showMessageDialog(frame, "Tobias Bodensteiner, Sven Gebauer, Tobias Grömer, Katharina Hauer, Valentin Kellner, Elena Menzl, Jonas Piehler, Alexander Puff, Maximilian Rauch, Catrin Schnupfhagn, Rudolf Wimmer, Matthias Zetzl");

	}

	public void onMenuStopSolver() {
		// TODO Auto-generated method stub

	}
}
