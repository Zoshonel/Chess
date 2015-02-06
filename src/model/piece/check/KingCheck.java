package model.piece.check;

import model.piece.King;
import model.plateform.Table;

public class KingCheck implements ICheck {
	private final King king;

	public KingCheck(King king) {
		this.king = king;
	}

	@Override
	public void check(Table table) {
		int row = this.king.getPosition().getRowNumber(); // Current row-position of the king.
		int column = this.king.getPosition().getColumnNumber(); // Current column-position of the king.
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (i != 0 || j != 0) { // At least i or j != 0 so the king can move
					if ((row + i >= 1 && row + i <= 8) && (column + j >= 1 && column + j <= 8)) { // The king can move within the chessboard, it won't get outside the board (square with coordination > 8)
						table.getSquare(row + i, column + j).getCheckedBy().add(this.king);
					}
				}
			}
		}
	}
}