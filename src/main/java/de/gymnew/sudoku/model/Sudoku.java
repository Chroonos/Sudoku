package de.gymnew.sudoku.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Sudoku {

	private Field[][] fields;
	private Row[] rows;
	private Column[] columns;
	private Block[][] blocks;
	public Sudoku() {
		fields = new Field[9][9];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				fields[i][j] = new Field();
			}
		}

		columns = new Column[9];

		for (int i = 0; i < 9; i++) {
			columns[i] = new Column(i, this);
		}

		rows = new Row[9];

		for (int i = 0; i < 9; i++) {
			rows[i] = new Row(i, this);
		}

		blocks = new Block[3][3];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int r, s;
				r = i * 3;
				s = j * 3;
				blocks[i][j] = new Block(r, s, this);
			}
		}
	}

	public Field getField(int column, int row) {
		return fields[column][row];
	}

	@Override
	public Sudoku clone() {
		Sudoku sudoku = new Sudoku();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Field src = fields[i][j];
				Field dst = sudoku.getField(i, j);
				dst.setValue(src.getValue());
				dst.addNotes(src.getNotes());
				dst.setLocked(src.isLocked());
			}
		}
		return sudoku;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				s += fields[i][j].getValue();
			}
			s += "|";
		}
		return s;
	}

	public boolean isValid() { // TODO This is bugged!
		for (Row r : rows) {
			if (r.isValid() == false) {
				return false;
			}
		}

		for (Column c : columns) {
			if (c.isValid() == false) {
				return false;
			}
		}

		for (Block[] b1 : blocks) {
			for (Block b2 : b1) {
				if (b2.isValid() == false) {
					return false;
				}
			}
		}
		
		return true;
	}

	private void load(String string) throws IOException {
		for (int i = 0; i < 9; i++) {
			int r = i * 10;
			for (int j = 0; j < 9; j++) {

				try {
					byte value = Byte.parseByte(string.substring(r + j, r + j + 1));
					fields[i][j].setValue(value);
					if(value != 0) fields[i][j].setLocked(true);
				} catch (NumberFormatException e) {
					throw new IOException("No number");
				}
			}

			if (String.valueOf(string.charAt(r + 9)).equals("|") == false) {
				throw new IOException("False sign");
			}
		}

		/*if (isValid() == false) {
			throw new IOException(); // TODO Fix this
		}*/
	}

	public static Sudoku load(File file) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		Sudoku s = new Sudoku();
		s.load(br.readLine());
		br.close();
		fr.close();
		return s;
	}

	public void save(File file) throws IOException {
		FileWriter fw = new FileWriter(file);
		fw.write(toString());
		fw.flush();
		fw.close();
	}
	
	public Block[][] getBlocks(){
		return blocks;
	}
	
	public Column[] getColumns(){
		return columns;
	}
	
	public Row[] getRows(){
		return rows;
	}
}
