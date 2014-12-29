package model.piece;

import model.plateform.Square;
import model.plateform.Table;

public class Rook extends Piece {
	public Rook(String color, Square position) {
		super(color, position);
	}

	@Override
	protected boolean validMove(Square destination, Table table) {
		if ((destination.getColumnNumber() != this.getPosition().getColumnNumber()) && (destination.getRowNumber() != this.getPosition().getRowNumber())) { // Move to another row and column is forbidden for rook
			return false;
		} else if (destination.getRowNumber() == this.position.getRowNumber()) { // Rook move horizontally
			int rowNumber = this.position.getRowNumber();
			if (this.position.getColumnNumber() > destination.getColumnNumber()) { // Move right to left
				for (int i = destination.getColumnNumber() + 1; i < this.position.getColumnNumber(); i++) {
					if (table.getSquare(rowNumber, i).isOccupied()) { // Verify if there have no occupied square in his path
						return false;
					}
				}
			} else { // Move left to right : same
				for (int i = this.position.getColumnNumber() + 1; i < destination.getColumnNumber(); i++) {
					if (table.getSquare(rowNumber, i).isOccupied()) { // Verify if there have no occupied square in his path
						return false;
					}
				}
			}
		} else if (destination.getColumnNumber() == this.position.getColumnNumber()) { // Rook move vertically
			int columnNumber = this.position.getColumnNumber();
			if (this.position.getRowNumber() > destination.getRowNumber()) { // Move down
				for (int i = destination.getRowNumber() + 1; i < this.position.getRowNumber(); i++) {
					if (table.getSquare(i, columnNumber).isOccupied()) { // Verify if there have no occupied square in his path
						return false;
					}
				}
			} else { // Move up
				for (int i = this.position.getRowNumber() + 1; i < destination.getRowNumber(); i++) {
					if (table.getSquare(i, columnNumber).isOccupied()) { // Verify if there have no occupied square in his path
						return false;
					}
				}
			}
		} else if (destination.getPiece().getColor().equalsIgnoreCase(this.color)) { // Rook can't capture piece with same color as itself
			return false;
		}
		return true;
	}
}