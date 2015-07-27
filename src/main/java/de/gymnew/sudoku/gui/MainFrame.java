package de.gymnew.sudoku.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import de.gymnew.sudoku.core.Solver;
import de.gymnew.sudoku.model.Sudoku;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private MainFrameHandler handler;
	private SudokuPanel panel;
	private int scaling;
	private Solver solver;
	
	public MainFrame() {
		
		/*==================================================*/
		// The Basics
		/*==================================================*/
		
		handler = new MainFrameHandler(this);
		
		setSize(800, 600);
		setResizable(false);
		
		setTitle("SudokuSolver");
		
		panel = new SudokuPanel();
		panel.addMouseListener(handler);
		panel.setOpaque(true);
		setContentPane(panel);
		
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent event) {
				handler.onMenuQuit();
			}
		});
		
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
		
		JMenuItem itemCredits = new JMenuItem("Credits");
		itemCredits.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				handler.onMenuCredits();
			}
		});
		menuSudoku.add(itemCredits);
		
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
				handler.onMenuStopSolver();
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

	public int getScaling() {
		return scaling;
	}

	public void setScaling(int scaling) {
		this.scaling = scaling;
		// TODO update frame size
		repaint();
	}
	
	public Sudoku getSudoku(){
		return panel.getSudoku();
	}
	
	public void setSudoku(Sudoku sudoku){
		panel.setSudoku(sudoku);
	}

	public Solver getSolver() {
		return solver;
	}

	public void setSolver(Solver solver) {
		this.solver = solver;
	}
}
