package model.piece;

import model.plateform.Square;
import model.plateform.Table;

public class King extends Piece {
	public King(String color, Square position) {
		super(color, position);
	}

	@Override
	public boolean validMove(Square destination, Table table) {
		return false;
	}
}