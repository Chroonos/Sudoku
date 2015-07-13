package de.gymnew.sudoku.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private MainFrameHandler handler;
	private SudokuPanel panel;
	
	public MainFrame() {
		
		/*==================================================*/
		// The Basics
		/*==================================================*/
		
		handler = new MainFrameHandler(this);
		
		setSize(800, 600);
		setResizable(false);
		
		setTitle("SudokuSolver");
		
		panel = new SudokuPanel();
		
		setContentPane(panel);
		
		/*==================================================*/
		// The Menu
		/*==================================================*/
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		
		JMenu menuSudoku = new JMenu("Sudoku");
		menuBar.add(menuSudoku);
		
		JMenuItem itemLoad = new JMenuItem("Laden");
		itemLoad.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				handler.onMenuLoad();
			}
		});
		menuSudoku.add(itemLoad);
		
		JMenuItem itemSave = new JMenuItem("Speichern");
		itemSave.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				handler.onMenuSave();
			}
		});
		menuSudoku.add(itemSave);
		
		menuSudoku.addSeparator();
		
		JMenuItem itemQuit = new JMenuItem("Beenden");
		itemQuit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				handler.onMenuQuit();
			}
		});
		menuSudoku.add(itemQuit);
		
		
		JMenu menuSolve = new JMenu("Lösen");
		menuBar.add(menuSolve);
		
		JMenuItem itemSolverStart = new JMenuItem("Löser starten");
		itemSolverStart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				handler.onMenuStartSolver();
			}
		});
		menuSolve.add(itemSolverStart);
		
		JMenuItem itemSolverStop = new JMenuItem("Löser stoppen");
		itemSolverStop.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				handler.onMenuStartSolver();
			}
		});
		menuSolve.add(itemSolverStop);
		
		menuSolve.addSeparator();
		
		JMenu menuAlgorithm = new JMenu("Algorithmus");
		menuSolve.add(menuAlgorithm);
		
		/*==================================================*/
		// The DrawPane
		/*==================================================*/
		
	}
}
