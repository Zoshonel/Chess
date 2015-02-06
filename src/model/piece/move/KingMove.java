package model.piece.move;

import model.piece.King;
import model.piece.Piece;
import model.plateform.Square;
import model.plateform.Table;
import model.plateform.Team;

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
		if (validMove(position, destination, table, this.king.getTeam())) {
			this.king.setFirstMove(false);
			position.empty();
			this.king.removeCheck(table);
			takeSquare(destination);
			this.king.check(table);
			return true;
		} else {
			return false;
		}
	}

	public boolean validMove(Square position, Square destination, Table table, Team team) {
		if (destination.getColumnNumber() - position.getColumnNumber() > 1) { // King can't move further than 1 square
			return false;
		} else if (destination.getRowNumber() - position.getRowNumber() > 1) {
			return false;
		} else if (destination.underCheckedBy(team.getOpponent())) { // Or move to a square checked by opponent team
			System.out.println("This square is under check by opponent");
			return false;
		} else if (destination.isOccupied()) {
			if (destination.getPiece().getTeam().equals(team)) { // Or capture his own piece
				System.out.println("Can't capture piece in same team");
				return false;
			}
		}

		if (destination.isOccupied()) { // If this move capture opponent piece
			Piece takenPiece = destination.getPiece();
			takenPiece.removeCheck(table); // Remove the check of this piece on the table.
			Team opponent = takenPiece.getTeam();
			opponent.getPieceList().remove(takenPiece); // And remove this piece on the opponent team;
		}
		return true;
	}

	private void takeSquare(Square destination) {
		this.king.setPosition(destination); // Change the current position to the destination
		destination.takenBy(this.king); // Alarm the square that it is taken by this piece
	}
}