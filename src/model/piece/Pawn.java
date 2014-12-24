package model.piece;

import model.plateform.Square;
import model.plateform.Table;

public class Pawn extends Piece implements Action {
	boolean firstMove = true;

	public Pawn(String color, Square position, Table table) {
		super(color, position, table);
	}

	public boolean move(Square destination) {
		if (validMove(destination)) {
			this.firstMove = false;
			this.position.empty();
			takeSquare(destination);
			return true;
		} else {
			return false;
		}
	}

	public boolean validMove(Square destination) {
		if (this.color == "white") {
			if (destination.getRowNumber() < this.position.getRowNumber()) { // Move backward is forbidden
				return false;
			}
			if (this.firstMove) { // Pawn's first move could go up to 2 squares
				if ((destination.getRowNumber() - this.position.getRowNumber()) > 2) { // So, any move higher than 2 squares is forbidden
					return false;
				}
				if ((destination.getRowNumber() - this.position.getRowNumber()) == 2) { // In case pawn move 2 squares :
					if (destination.getColumnNumber() != this.position.getRowNumber()) { // Pawn have to move straigh ahead
						return false;
					}
					if (destination.isOccupied()) { // And the destination must not be occupied
						return false;
					}
					if (this.table.getSquare(this.position.getRowNumber() + 1, this.position.getColumnNumber()).isOccupied()) { // Or if the square ahead is occupied, pawn can't move.
						return false;
					}
				}
				if ((destination.getRowNumber() - this.position.getRowNumber()) == 1) { // In case pawn moves 1 square : we investigate the possibility of column change (pawn changes column only when it capture opponent).
					if (destination.getColumnNumber() == this.position.getColumnNumber()) { // In case it moves 1 square ahead, not capture opponent :
						if (destination.isOccupied()) { // We check if the column is occupied
							return false;
						}
					}
					if (Math.abs(destination.getColumnNumber() - this.position.getColumnNumber()) > 1) { // His destination can't be further than 1 colum comparing to the initial position.
						return false;
					}
					if (!destination.isOccupied()) {
						return false;
					} else {
						if (destination.getPiece().getColor() == this.color) {
							return false;
						} else {
							return true;
						}
					}
				}
			} else {
				if ((destination.getRowNumber() - this.position.getRowNumber()) > 1) {
					return false;
				}
				if (destination.getColumnNumber() == this.position.getColumnNumber()) { // In case it moves 1 square ahead, not capture opponent :
					if (destination.isOccupied()) { // We check if the column is occupied
						return false;
					}
				}
				if (Math.abs(destination.getColumnNumber() - this.position.getColumnNumber()) > 1) { // His destination can't be further than 1 colum comparing to the initial position.
					return false;
				}
				if (!destination.isOccupied()) {
					return false;
				} else {
					if (destination.getPiece().getColor() == this.color) {
						return false;
					} else {
						return true;
					}
				}
			}
		} else { // If the piece is black, we reverse the substract destination - initial position
			if (destination.getRowNumber() > this.position.getRowNumber()) { // Move backward is forbidden
				return false;
			}
			if (this.firstMove) { // Pawn's first move could go up to 2 squares
				if ((this.position.getRowNumber() - destination.getRowNumber()) > 2) { // So, any move higher than 2 squares is forbidden
					return false;
				}
				if ((this.position.getRowNumber() - destination.getRowNumber()) == 2) { // In case pawn move 2 squares :
					if (destination.getColumnNumber() != this.position.getRowNumber()) { // Pawn have to move straigh ahead
						return false;
					}
					if (destination.isOccupied()) { // And the destination must not be occupied
						return false;
					}
					if (this.table.getSquare(this.position.getRowNumber() - 1, this.position.getColumnNumber()).isOccupied()) { // Or if the square ahead is occupied, pawn can't move.
						return false;
					}
				}
				if ((this.position.getRowNumber() - destination.getRowNumber()) == 1) { // In case pawn moves 1 square : we investigate the possibility of column change (pawn changes column only when it capture opponent).
					if (destination.getColumnNumber() == this.position.getColumnNumber()) { // In case it moves 1 square ahead, not capture opponent :
						if (destination.isOccupied()) { // We check if the column is occupied
							return false;
						}
					}
					if (Math.abs(destination.getColumnNumber() - this.position.getColumnNumber()) > 1) { // His destination can't be further than 1 colum comparing to the initial position.
						return false;
					}
					if (!destination.isOccupied()) {
						return false;
					} else {
						if (destination.getPiece().getColor() == this.color) {
							return false;
						} else {
							return true;
						}
					}
				}
			} else {
				if ((this.position.getRowNumber() - destination.getRowNumber()) > 1) {
					return false;
				}
				if (destination.getColumnNumber() == this.position.getColumnNumber()) { // In case it moves 1 square ahead, not capture opponent :
					if (destination.isOccupied()) { // We check if the column is occupied
						return false;
					}
				}
				if (Math.abs(destination.getColumnNumber() - this.position.getColumnNumber()) > 1) { // His destination can't be further than 1 colum comparing to the initial position.
					return false;
				}
				if (!destination.isOccupied()) {
					return false;
				} else {
					if (destination.getPiece().getColor() == this.color) {
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
		this.position = destination;
		destination.takenBy(this);
	}
}