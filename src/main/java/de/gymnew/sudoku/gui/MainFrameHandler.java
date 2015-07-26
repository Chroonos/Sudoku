package de.gymnew.sudoku.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFileChooser;

public class MainFrameHandler implements MouseListener{
	
	private MainFrame frame;
	
	public MainFrameHandler(MainFrame frame) {
		this.frame = frame;
	}
	
	/*==================================================*/
	// From DrawPanel
	/*==================================================*/

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
	
	/*==================================================*/
	// From MainFrame
	/*==================================================*/
	
	public void onMenuQuit() {
		System.exit(0);
		
	}
	
	public void onMenuLoad() {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File file = chooser.getSelectedFile();
		if(file != null){
			//TODO
		}
	}
	
	public void onMenuSave() {
		// TODO Auto-generated method stub
		
	}

	public void onMenuStartSolver() {
		// TODO Auto-generated method stub
		
	}

	public void onMenuCredits() {
		// TODO Auto-generated method stub
		
	}

	public void onMenuStopSolver() {
		// TODO Auto-generated method stub
		
	}
}
