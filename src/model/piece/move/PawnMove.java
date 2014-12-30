package model.piece.move;

import model.piece.Pawn;
import model.plateform.Square;
import model.plateform.Table;
import model.plateform.Team;

public class PawnMove implements IMove {
	private final Pawn pawn;

	public PawnMove(Pawn pawn) {
		this.pawn = pawn;
	}

	@Override
	public boolean move(Square destination, Table table) {
		Square position = this.pawn.getPosition();
		if (position.equals(destination)) {
			return false;
		}
		if (validMove(position, destination, table, this.pawn.getTeam())) {
			this.pawn.setFirstMove(false);
			position.empty();
			takeSquare(destination);
			return true;
		} else {
			return false;
		}
	}

	private boolean validMove(Square position, Square destination, Table table, Team team) {
		if (team.getColor() == "white") {
			if (destination.getRowNumber() < position.getRowNumber()) { // Move backward is forbidden
				return false;
			}
			if (this.pawn.isFirstMove()) { // Pawn's first move could go up to 2 squares
				if ((destination.getRowNumber() - position.getRowNumber()) > 2) { // So, any move higher than 2 squares is forbidden
					return false;
				}
				if ((destination.getRowNumber() - position.getRowNumber()) == 2) { // In case pawn move 2 squares :
					if (destination.getColumnNumber() != position.getRowNumber()) { // Pawn have to move straigh ahead
						return false;
					}
					if (destination.isOccupied()) { // And the destination must not be occupied
						return false;
					}
					if (table.getSquare(position.getRowNumber() + 1, position.getColumnNumber()).isOccupied()) { // Or if the square ahead is occupied, pawn can't move.
						return false;
					}
				}
				if ((destination.getRowNumber() - position.getRowNumber()) == 1) { // In case pawn moves 1 square : we investigate the possibility of column change (pawn changes column only when it capture opponent).
					if (destination.getColumnNumber() == position.getColumnNumber()) { // In case it moves 1 square ahead, not capture opponent :
						if (destination.isOccupied()) { // We check if the column is occupied
							return false;
						}
					}
					if (Math.abs(destination.getColumnNumber() - position.getColumnNumber()) > 1) { // His destination can't be further than 1 colum comparing to the initial position.
						return false;
					}
					if (!destination.isOccupied()) {
						return false;
					} else {
						if (destination.getPiece().getTeam().equals(team)) {
							return false;
						} else {
							return true;
						}
					}
				}
			} else {
				if ((destination.getRowNumber() - position.getRowNumber()) > 1) {
					return false;
				}
				if (destination.getColumnNumber() == position.getColumnNumber()) { // In case it moves 1 square ahead, not capture opponent :
					if (destination.isOccupied()) { // We check if the column is occupied
						return false;
					}
				}
				if (Math.abs(destination.getColumnNumber() - position.getColumnNumber()) > 1) { // His destination can't be further than 1 colum comparing to the initial position.
					return false;
				}
				if (!destination.isOccupied()) {
					return false;
				} else {
					if (destination.getPiece().getTeam().equals(team)) {
						return false;
					} else {
						return true;
					}
				}
			}
		} else { // If the piece is black, we reverse the substract destination - initial position
			if (destination.getRowNumber() > position.getRowNumber()) { // Move backward is forbidden
				return false;
			}
			if (this.pawn.isFirstMove()) { // Pawn's first move could go up to 2 squares
				if ((position.getRowNumber() - destination.getRowNumber()) > 2) { // So, any move higher than 2 squares is forbidden
					return false;
				}
				if ((position.getRowNumber() - destination.getRowNumber()) == 2) { // In case pawn move 2 squares :
					if (destination.getColumnNumber() != position.getRowNumber()) { // Pawn have to move straigh ahead
						return false;
					}
					if (destination.isOccupied()) { // And the destination must not be occupied
						return false;
					}
					if (table.getSquare(position.getRowNumber() - 1, position.getColumnNumber()).isOccupied()) { // Or if the square ahead is occupied, pawn can't move.
						return false;
					}
				}
				if ((position.getRowNumber() - destination.getRowNumber()) == 1) { // In case pawn moves 1 square : we investigate the possibility of column change (pawn changes column only when it capture opponent).
					if (destination.getColumnNumber() == position.getColumnNumber()) { // In case it moves 1 square ahead, not capture opponent :
						if (destination.isOccupied()) { // We check if the column is occupied
							return false;
						}
					}
					if (Math.abs(destination.getColumnNumber() - position.getColumnNumber()) > 1) { // His destination can't be further than 1 colum comparing to the initial position.
						return false;
					}
					if (!destination.isOccupied()) {
						return false;
					} else {
						if (destination.getPiece().getTeam().equals(team)) {
							return false;
						} else {
							return true;
						}
					}
				}
			} else {
				if ((position.getRowNumber() - destination.getRowNumber()) > 1) {
					return false;
				}
				if (destination.getColumnNumber() == position.getColumnNumber()) { // In case it moves 1 square ahead, not capture opponent :
					if (destination.isOccupied()) { // We check if the column is occupied
						return false;
					}
				}
				if (Math.abs(destination.getColumnNumber() - position.getColumnNumber()) > 1) { // His destination can't be further than 1 colum comparing to the initial position.
					return false;
				}
				if (!destination.isOccupied()) {
					return false;
				} else {
					if (destination.getPiece().getTeam().equals(team)) {
						return false;
					} else {
						return true;
					}
				}
			}
		}
		return true;
	}

	private void takeSquare(Square destination) {
		this.pawn.setPosition(destination); // Change the current position to the destination
		destination.takenBy(this.pawn); // Alarm the square that it is taken by this piece
	}
}