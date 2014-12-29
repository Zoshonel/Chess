package model.piece;

import model.plateform.Square;
import model.plateform.Table;

public class Knight extends Piece {
	public Knight(String color, Square position) {
		super(color, position);
	}

	@Override
	protected boolean validMove(Square destination, Table table) {
		int deltaColumn = destination.getColumnNumber() - this.position.getColumnNumber();
		int deltaRow = destination.getRowNumber() - this.position.getRowNumber();
		int x = deltaColumn * deltaColumn + deltaRow * deltaRow;
		if (x != 5) {
			return false;
		} else if (destination.getPiece().getColor().equalsIgnoreCase(this.color)) { // Knight can't capture piece with same color as itself
			return false;
		}
		return true;
	}
}