package model.piece.check;

import model.piece.Bishop;
import model.plateform.Square;
import model.plateform.Table;

public class BishopCheck implements ICheck {
	private final Bishop bishop;

	public BishopCheck(Bishop bishop) {
		this.bishop = bishop;
	}

	@Override
	public void check(Table table) {
		int row = this.bishop.getPosition().getRowNumber(); // Current row-position of the bishop.
		int column = this.bishop.getPosition().getColumnNumber(); // Current column-position of the bishop.
		boolean next = true;
		int i = 1;
		while (next) {
			if (row - i >= 1 && column - i >= 1) { // Move downward to the left.
				Square square = table.getSquare(row - i, column - i);
				square.getCheckedBy().add(this.bishop);
				if (!square.isOccupied()) {
					i++;
				} else {
					next = false;
				}
			} else {
				next = false;
			}
		}
		next = true;
		i = 1;
		while (next) {
			if (row - i >= 1 && column + i <= 8) { // Move downward to the right.
				Square square = table.getSquare(row - i, column + i);
				square.getCheckedBy().add(this.bishop);
				if (!square.isOccupied()) {
					i++;
				} else {
					next = false;
				}
			} else {
				next = false;
			}
		}
		next = true;
		i = 1;
		while (next) {
			if (row + i <= 8 && column - i >= 1) { // Move upward to the left.
				Square square = table.getSquare(row + i, column - i);
				square.getCheckedBy().add(this.bishop);
				if (!square.isOccupied()) {
					i++;
				} else {
					next = false;
				}
			} else {
				next = false;
			}
		}
		next = true;
		i = 1;
		while (next) {
			if (row + i <= 8 && column + i <= 8) { // Move upward to the right.
				Square square = table.getSquare(row + i, column + i);
				square.getCheckedBy().add(this.bishop);
				if (!square.isOccupied()) {
					i++;
				} else {
					next = false;
				}
			} else {
				next = false;
			}
		}
	}

	@Override
	public void removeCheck(Table table) {
		// TODO Auto-generated method stub

	}
}