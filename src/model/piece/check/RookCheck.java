package model.piece.check;

import model.piece.Rook;
import model.plateform.Square;
import model.plateform.Table;

public class RookCheck implements ICheck {
	private final Rook rook;

	public RookCheck(Rook roook) {
		this.rook = roook;
	}

	@Override
	public void check(Table table) {
		int row = this.rook.getPosition().getRowNumber(); // Current row-position of the rook.
		int column = this.rook.getPosition().getColumnNumber(); // Current column-position of the rook.
		boolean next = true;
		int i = 1;
		while (next) {
			if (column - i > 0) { // Move to the left.
				Square square = table.getSquare(row, column - i);
				square.getCheckedBy().add(this.rook);
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
				square.getCheckedBy().add(this.rook);
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
				square.getCheckedBy().add(this.rook);
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
				square.getCheckedBy().add(this.rook);
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