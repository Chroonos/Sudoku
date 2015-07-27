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
			}
		}
		return null;
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

	private void load(String string) throws IOException {
		// TODO check sudoku

		for (int i = 0; i < 9; i++) {
			int r = i * 9;
			for (int j = 0; j < 9; j++) {

				try {
					byte value = Byte.parseByte(String.valueOf(string.charAt(j + r)));
					fields[i][j].setValue(value);
				} catch (NumberFormatException e) {
					throw new IOException("No Number");
				}
			}

			if (String.valueOf(string.charAt(r + 9)).equals("|") == false) {
				throw new IOException("False Sign");
			}
		}
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

	public void save(File file) throws IOException{
		FileWriter fw = new FileWriter(file);
		fw.write(toString());
		fw.flush();
		fw.close();
	}
}
