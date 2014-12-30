package model.piece;

import model.plateform.Square;
import model.plateform.Table;
import model.plateform.Team;

public class Knight extends Piece {
	public Knight(Team team, Square position) {
		super(team, position);
	}

	@Override
	protected boolean validMove(Square destination, Table table) {
		int deltaColumn = destination.getColumnNumber() - this.position.getColumnNumber();
		int deltaRow = destination.getRowNumber() - this.position.getRowNumber();
		int x = deltaColumn * deltaColumn + deltaRow * deltaRow;
		if (x != 5) {
			return false;
		} else if (destination.getPiece().getTeam().equals(this.team)) { // Knight can't capture piece with same color as itself
			return false;
		}
		return true;
	}
}