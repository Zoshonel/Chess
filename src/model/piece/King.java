package model.piece;

import model.plateform.Square;
import model.plateform.Table;

public class King extends Piece implements Action {
	public King(String color, Square position, Table table) {
		super(color, position, table);
	}

	public boolean move(Square destination) {
		return true;
	}

	public void capture(Square destination) {

	}

	public boolean validMove(Square destination) {
		return false;
	}
}