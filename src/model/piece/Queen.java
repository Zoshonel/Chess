package model.piece;

import model.plateform.Square;

public class Queen extends Piece {
	public Queen() {
		super();
	}

	@Override
	public void move(Square destination) {

	}

	@Override
	public void eat(Square destination) {

	}

	@Override
	public boolean validMove(Square destination) {
		return false;
	}
}