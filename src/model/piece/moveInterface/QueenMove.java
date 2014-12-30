package model.piece.moveInterface;

import model.piece.Queen;
import model.plateform.Square;
import model.plateform.Table;

public class QueenMove implements IMove {
	private final Queen queen;

	public QueenMove(Queen queen) {
		this.queen = queen;
	}

	@Override
	public boolean move(Square destination, Table table) {
		Square position = this.queen.getPosition();
		if (position.equals(destination)) {
			return false;
		}
		if (validMove(position, destination, table)) {
			position.empty();
			takeSquare(destination);
			return true;
		} else {
			return false;
		}
	}

	private boolean validMove(Square position, Square destination, Table table) {
		// TODO Auto-generated method stub
		return false;
	}

	private void takeSquare(Square destination) {
		this.queen.setPosition(destination); // Change the current position to the destination
		destination.takenBy(this.queen); // Alarm the square that it is taken by this piece
	}
}