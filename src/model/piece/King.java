package model.piece;

import model.plateform.Square;
import model.plateform.Table;
import model.plateform.Team;

public class King extends Piece {

	public King(Team team, Square position) {
		super(team, position);
	}

	@Override
	public boolean validMove(Square destination, Table table) {
		if (destination.getColumnNumber() - this.position.getColumnNumber() > 1) { // King can't move further than 1 square
			return false;
		} else if (destination.getRowNumber() - this.position.getRowNumber() > 1) {
			return false;
		} else if (destination.isUnderCheck()) { // Or move to a checked square
			return false;
		} else if (destination.getPiece().getTeam().equals(this.team)) { // Or capture his own piece
			return false;
		}
		return false;
	}
}