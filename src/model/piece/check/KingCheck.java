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
				if (i != 0 || j != 0) {
					table.getSquare(row + i, column + j).getCheckedBy().add(this.king);
				}
			}
		}
	}
}