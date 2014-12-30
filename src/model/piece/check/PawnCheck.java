package model.piece.check;

import model.piece.Pawn;
import model.plateform.Table;

public class PawnCheck implements ICheck {
	private final Pawn pawn;

	public PawnCheck(Pawn pawn) {
		this.pawn = pawn;
	}

	@Override
	public void check(Table table) {
		int row = this.pawn.getPosition().getRowNumber();
		int column = this.pawn.getPosition().getColumnNumber();
		if (this.pawn.getTeam().getColor().equalsIgnoreCase("white")) {
			if (row > 0 && row < 8) {
				if (column == 1) {
					table.getSquare(row + 1, column + 1).getCheckedBy().add(this.pawn);
				} else if (column == 8) {
					table.getSquare(row + 1, column - 1).getCheckedBy().add(this.pawn);
				} else {
					table.getSquare(row + 1, column + 1).getCheckedBy().add(this.pawn);
					table.getSquare(row + 1, column - 1).getCheckedBy().add(this.pawn);
				}
			}
		} else if (this.pawn.getTeam().getColor().equalsIgnoreCase("black")) {
			if (row > 0 && row < 8) {
				if (column == 1) {
					table.getSquare(row - 1, column + 1).getCheckedBy().add(this.pawn);
				} else if (column == 8) {
					table.getSquare(row - 1, column - 1).getCheckedBy().add(this.pawn);
				} else {
					table.getSquare(row - 1, column + 1).getCheckedBy().add(this.pawn);
					table.getSquare(row - 1, column - 1).getCheckedBy().add(this.pawn);
				}
			}
		}
	}
}