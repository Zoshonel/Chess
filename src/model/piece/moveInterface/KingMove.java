package model.piece.moveInterface;

import model.piece.King;
import model.plateform.Square;
import model.plateform.Table;

public class KingMove implements IMove {
	private final King king;

	public KingMove(King king) {
		this.king = king;
	}

	@Override
	public boolean move(Square destination, Table table) {
		Square position = this.king.getPosition();
		if (position.equals(destination)) {
			return false;
		}
		if (validMove(position, destination, table)) {
			this.king.setFirstMove(false);
			position.empty();
			takeSquare(destination);
			return true;
		} else {
			return false;
		}
	}

	public boolean validMove(Square position, Square destination, Table table) {
		if (destination.getColumnNumber() - position.getColumnNumber() > 1) { // King can't move further than 1 square
			return false;
		} else if (destination.getRowNumber() - position.getRowNumber() > 1) {
			return false;
		} else if (destination.isUnderCheck()) { // Or move to a checked square
			return false;
		} else if (destination.getPiece().getTeam().equals(this.king.getTeam())) { // Or capture his own piece
			return false;
		}
		return false;
	}

	private void takeSquare(Square destination) {
		this.king.setPosition(destination); // Change the current position to the destination
		destination.takenBy(this.king); // Alarm the square that it is taken by this piece
	}
}