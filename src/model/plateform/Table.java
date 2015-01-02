package model.plateform;

import java.util.ArrayList;
import java.util.List;

public class Table {
	private final List<Square> listSquare;

	public Table() {
		this.listSquare = new ArrayList<Square>();
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				if ((i + j) % 2 == 0) {
					Square blackSquare = new Square(i, j, "black");
					this.listSquare.add(blackSquare);
				} else {
					Square whiteSquare = new Square(i, j, "white");
					this.listSquare.add(whiteSquare);
				}
			}
		}
	}

	/**
	 * @return the listSquare
	 */
	public List<Square> getListSquare() {
		return this.listSquare;
	}

	/**
	 * @param row
	 * @param column
	 * @return return the square that match row, column
	 */
	public Square getSquare(int row, int column) {
		if (row < 0 || row > 8 || column < 0 || column > 8) {
			return null;
		} else {
			for (Square square : this.listSquare) {
				if ((square.getColumnNumber() == column) && (square.getRowNumber() == row)) {
					return square;
				}
			}
			return null;
		}
	}

	public List<Square> getRow(int rowNumber) {
		List<Square> row = new ArrayList<Square>();
		for (Square square : this.listSquare) {
			if (square.getRowNumber() == rowNumber) {
				row.add(square);
			}
		}
		return row;
	}

	public List<Square> getColumn(int columnNumber) {
		List<Square> column = new ArrayList<Square>();
		for (Square square : this.listSquare) {
			if (square.getColumnNumber() == columnNumber) {
				column.add(square);
			}
		}
		return column;
	}
}