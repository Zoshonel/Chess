package model.piece.check;

import model.piece.Queen;
import model.plateform.Square;
import model.plateform.Table;

public class QueenCheck implements ICheck {
	private final Queen queen;

	public QueenCheck(Queen queen) {
		this.queen = queen;
	}

	@Override
	public void check(Table table) {
		int row = this.queen.getPosition().getRowNumber(); // Current row-position of the queen.
		int column = this.queen.getPosition().getColumnNumber(); // Current column-position of the queen.
		boolean next = true;
		int i = 1;
		while (next) {
			if (column - i > 0) { // Move to the left.
				Square square = table.getSquare(row, column - i);
				square.getCheckedBy().add(this.queen);
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
			if (column + i <= 8) { // Move to the right.
				Square square = table.getSquare(row, column + i);
				square.getCheckedBy().add(this.queen);
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
			if (row - i > 0) { // Move downward.
				Square square = table.getSquare(row - i, column);
				square.getCheckedBy().add(this.queen);
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
			if (row + i <= 8) { // Move upward.
				Square square = table.getSquare(row + i, column);
				square.getCheckedBy().add(this.queen);
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
			if (row - i >= 1 && column - i >= 1) { // Move downward to the left.
				Square square = table.getSquare(row - i, column - i);
				square.getCheckedBy().add(this.queen);
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
				square.getCheckedBy().add(this.queen);
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
				square.getCheckedBy().add(this.queen);
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
				square.getCheckedBy().add(this.queen);
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