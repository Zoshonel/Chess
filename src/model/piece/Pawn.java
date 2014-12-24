package model.piece;

import model.plateform.Square;

public class Pawn extends Piece {
	boolean firstMove = true;

	public Pawn() {
		super();
	}

	@Override
	public void move(Square destination) {
		if (validMove(destination)) {
			this.firstMove = false;
			this.position.empty();
			takeSquare(destination);
		} else {
			System.out.println("Invalid move");
		}
	}

	@Override
	public void eat(Square destination) {

	}

	@Override
	public boolean validMove(Square destination) {
		if (this.color == "white") {
			if (destination.getRowNumber() < this.position.getRowNumber()) {
				return false;
			}
			if (this.firstMove && ((destination.getRowNumber() - this.position.getRowNumber()) > 2)) {
				return false;
			}
			if (!this.firstMove && ((destination.getRowNumber() - this.position.getRowNumber()) > 1)) {
				return false;
			}
		} else {

		}
		return true;
	}

	private void takeSquare(Square destination) {
		this.position = destination;
		destination.takenBy(this);
	}
}