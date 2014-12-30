package model.piece.moveInterface;

import model.piece.Bishop;
import model.plateform.Square;
import model.plateform.Table;

public class BishopMove implements IMove {
	private final Bishop bishop;

	public BishopMove(Bishop bishop) {
		this.bishop = bishop;
	}

	@Override
	public boolean move(Square destination, Table table) {
		Square position = this.bishop.getPosition();
		if (position.equals(destination)) {
			return false;
		}
		if (validMove(position, destination, table)) {
			position.empty();
			takeSquare(destination);
			return true;
		} else {
			return false;
		}
	}

	private boolean validMove(Square position, Square destination, Table table) {
		if (Math.abs(destination.getColumnNumber() - position.getColumnNumber()) != Math.abs(destination.getRowNumber() - position.getRowNumber())) { // Bishop can only move diagonally
			return false;
		} else if (pathBlocked(position, destination, table)) { // Verify if there have no occupied square in his path
			return false;
		} else if (destination.getPiece().getTeam().equals(this.bishop.getTeam())) { // Bishop can't capture piece with same color as itself
			return false;
		}

		return true;
	}

	private boolean pathBlocked(Square position, Square destination, Table table) {
		int delta = Math.abs(destination.getColumnNumber() - position.getColumnNumber());
		if (destination.getRowNumber() > position.getRowNumber()) { // Move upward...
			if (destination.getColumnNumber() > position.getColumnNumber()) { // ...to the right
				for (int i = 1; i < delta; i++) {
					if (table.getSquare(position.getRowNumber() + i, position.getColumnNumber() + i).isOccupied()) {
						return true;
					}
				}
			} else { // ...to the left
				for (int i = 1; i < delta; i++) {
					if (table.getSquare(position.getRowNumber() + i, position.getColumnNumber() - i).isOccupied()) {
						return true;
					}
				}
			}
		} else { // Move downward...
			if (destination.getColumnNumber() > position.getColumnNumber()) { // ...to the right
				for (int i = 1; i < delta; i++) {
					if (table.getSquare(position.getRowNumber() - i, position.getColumnNumber() + i).isOccupied()) {
						return true;
					}
				}
			} else { // ...to the left
				for (int i = 1; i < delta; i++) {
					if (table.getSquare(position.getRowNumber() - i, position.getColumnNumber() - i).isOccupied()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private void takeSquare(Square destination) {
		this.bishop.setPosition(destination); // Change the current position to the destination
		destination.takenBy(this.bishop); // Alarm the square that it is taken by this piece
	}
}