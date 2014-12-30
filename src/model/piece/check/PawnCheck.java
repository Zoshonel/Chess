package model.piece.check;

import model.piece.Pawn;
import model.plateform.Square;
import model.plateform.Table;

public class PawnCheck implements ICheck {
	private final Pawn pawn;

	public PawnCheck(Pawn pawn) {
		this.pawn = pawn;
	}

	@Override
	public void check(Square position, Table table) {
		int row = position.getRowNumber();
		int column = position.getColumnNumber();
		if (this.pawn.getTeam().getColor().equalsIgnoreCase("white")) {
			if (row > 0 && row < 8) {
				if (column == 1) {
					table.getSquare(row + 1, column + 1).getCheckedBy().add(this.pawn.getTeam());
				} else if (column == 8) {
					table.getSquare(row + 1, column - 1).getCheckedBy().add(this.pawn.getTeam());
				} else {
					table.getSquare(row + 1, column + 1).getCheckedBy().add(this.pawn.getTeam());
					table.getSquare(row + 1, column - 1).getCheckedBy().add(this.pawn.getTeam());
				}
			}
		} else if (this.pawn.getTeam().getColor().equalsIgnoreCase("black")) {
			if (row > 0 && row < 8) {
				if (column == 1) {
					table.getSquare(row - 1, column + 1).getCheckedBy().add(this.pawn.getTeam());
				} else if (column == 8) {
					table.getSquare(row - 1, column - 1).getCheckedBy().add(this.pawn.getTeam());
				} else {
					table.getSquare(row - 1, column + 1).getCheckedBy().add(this.pawn.getTeam());
					table.getSquare(row - 1, column - 1).getCheckedBy().add(this.pawn.getTeam());
				}
			}
		}
	}
}