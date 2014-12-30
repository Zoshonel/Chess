package model.piece;

import model.plateform.Square;
import model.plateform.Table;
import model.plateform.Team;

public class Bishop extends Piece {
	public Bishop(Team team, Square position) {
		super(team, position);
	}

	@Override
	public boolean validMove(Square destination, Table table) {
		if (Math.abs(destination.getColumnNumber() - this.position.getColumnNumber()) != Math.abs(destination.getRowNumber() - this.position.getRowNumber())) { // Bishop can only move diagonally
			return false;
		} else if (pathBlocked(destination, table)) { // Verify if there have no occupied square in his path
			return false;
		} else if (destination.getPiece().getTeam().equals(this.team)) { // Bishop can't capture piece with same color as itself
			return false;
		}

		return true;
	}

	private boolean pathBlocked(Square destination, Table table) {
		int delta = Math.abs(destination.getColumnNumber() - this.position.getColumnNumber());
		if (destination.getRowNumber() > this.position.getRowNumber()) { // Move upward...
			if (destination.getColumnNumber() > this.position.getColumnNumber()) { // ...to the right
				for (int i = 1; i < delta; i++) {
					if (table.getSquare(this.position.getRowNumber() + i, this.position.getColumnNumber() + i).isOccupied()) {
						return true;
					}
				}
			} else { // ...to the left
				for (int i = 1; i < delta; i++) {
					if (table.getSquare(this.position.getRowNumber() + i, this.position.getColumnNumber() - i).isOccupied()) {
						return true;
					}
				}
			}
		} else { // Move downward...
			if (destination.getColumnNumber() > this.position.getColumnNumber()) { // ...to the right
				for (int i = 1; i < delta; i++) {
					if (table.getSquare(this.position.getRowNumber() - i, this.position.getColumnNumber() + i).isOccupied()) {
						return true;
					}
				}
			} else { // ...to the left
				for (int i = 1; i < delta; i++) {
					if (table.getSquare(this.position.getRowNumber() - i, this.position.getColumnNumber() - i).isOccupied()) {
						return true;
					}
				}
			}
		}
		return false;
	}
}