package model.piece;

import model.plateform.Square;

public abstract class Piece {
	protected String color;
	protected Square position;

	public void move(Square destination) {

	};

	public void eat(Square destination) {

	};

	protected boolean validMove(Square destination) {
		return false;
	};
}