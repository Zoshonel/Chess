package model.piece.move;

import model.piece.Rook;
import model.plateform.Square;
import model.plateform.Table;
import model.plateform.Team;

public class RookMove implements IMove {
	private final Rook rook;

	public RookMove(Rook rook) {
		this.rook = rook;
	}

	@Override
	public boolean move(Square destination, Table table) {
		Square position = this.rook.getPosition();
		if (position.equals(destination)) {
			return false;
		}
		if (validMove(position, destination, table, this.rook.getTeam())) {
			this.rook.setFirstMove(false);
			position.empty();
			takeSquare(destination);
			return true;
		} else {
			return false;
		}
	}

	public static boolean validMove(Square position, Square destination, Table table, Team team) {
		if ((destination.getColumnNumber() != position.getColumnNumber()) && (destination.getRowNumber() != position.getRowNumber())) { // Move to another row and column is forbidden for rook
			return false;
		} else if (pathBlocked(position, destination, table)) { // Verify if there have no occupied square in his path
			return false;
		} else if (destination.getPiece().getTeam().equals(team)) { // Rook can't capture piece with same color as itself
			return false;
		}
		return true;
	}

	private static boolean pathBlocked(Square position, Square destination, Table table) {
		if (destination.getRowNumber() == position.getRowNumber()) { // Rook move horizontally
			int rowNumber = position.getRowNumber();
			if (position.getColumnNumber() > destination.getColumnNumber()) { // Move right to left
				for (int i = destination.getColumnNumber() + 1; i < position.getColumnNumber(); i++) {
					if (table.getSquare(rowNumber, i).isOccupied()) {
						return true;
					}
				}
			} else { // Move left to right : same
				for (int i = position.getColumnNumber() + 1; i < destination.getColumnNumber(); i++) {
					if (table.getSquare(rowNumber, i).isOccupied()) {
						return true;
					}
				}
			}
		} else if (destination.getColumnNumber() == position.getColumnNumber()) { // Rook move vertically
			int columnNumber = position.getColumnNumber();
			if (position.getRowNumber() > destination.getRowNumber()) { // Move down
				for (int i = destination.getRowNumber() + 1; i < position.getRowNumber(); i++) {
					if (table.getSquare(i, columnNumber).isOccupied()) {
						return true;
					}
				}
			} else { // Move up
				for (int i = position.getRowNumber() + 1; i < destination.getRowNumber(); i++) {
					if (table.getSquare(i, columnNumber).isOccupied()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private void takeSquare(Square destination) {
		this.rook.setPosition(destination); // Change the current position to the destination
		destination.takenBy(this.rook); // Alarm the square that it is taken by this piece
	}
}