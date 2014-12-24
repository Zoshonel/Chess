package model.piece;

import model.plateform.Square;

public class Rook extends Piece {
	public Rook() {
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