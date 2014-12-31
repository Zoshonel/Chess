package model.piece.check;

import java.util.ArrayList;

import model.piece.Knight;
import model.plateform.Table;

public class KnightCheck implements ICheck {
	private final Knight knight;

	public KnightCheck(Knight knight) {
		this.knight = knight;
	}

	@Override
	public void check(Table table) {
		int row = this.knight.getPosition().getRowNumber(); // Current row-position of the knight.
		int column = this.knight.getPosition().getColumnNumber(); // Current column-position of the knight.

		// Place a knight on a chessboard to understand the code below :v
		ArrayList<Integer> availableRow = new ArrayList<Integer>();
		ArrayList<Integer> availableColumn = new ArrayList<Integer>();
		int deltaRowDown = 2;
		int deltaRowUp = 2;
		int deltaColumnLeft = 2;
		int deltaColumnRight = 2;
		while (row - deltaRowDown < 1) {
			deltaRowDown--;
		}
		while (row + deltaRowUp > 8) {
			deltaRowUp--;
		}
		while (column - deltaColumnLeft < 1) {
			deltaColumnLeft--;
		}
		while (column + deltaColumnRight > 8) {
			deltaColumnRight++;
		}
		for (int i = 1; i <= deltaRowDown; i++) {
			availableRow.add(row - i);
		}
		for (int i = 1; i <= deltaRowUp; i++) {
			availableRow.add(row + i);
		}
		for (int i = 1; i <= deltaColumnLeft; i++) {
			availableColumn.add(column - i);
		}
		for (int i = 1; i <= deltaColumnRight; i++) {
			availableColumn.add(column + i);
		}
		for (int x : availableRow) {
			for (int y : availableColumn) {
				if (Math.abs(x - row) + Math.abs(y - column) == 3) { // Test if the square is correct
					table.getSquare(x, y).getCheckedBy().add(this.knight);
				}
			}
		}
	}
}