package model.piece.move;

import model.piece.Piece;
import model.piece.Queen;
import model.plateform.Square;
import model.plateform.Table;
import model.plateform.Team;

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
		if (validMove(position, destination, table, this.queen.getTeam())) {
			position.empty(); // Empty the curren square
			this.queen.removeCheck(table);
			takeSquare(destination); // Move to the destination

			// Below is to verify if this move will let the king under check by opponent
			for (Piece piece : this.queen.getTeam().getOpponent().getPieceList()) {
				piece.removeCheck(table); // Refresh the table
				piece.check(table); // Replay the check for each of opponent piece
			}

			if (this.queen.getTeam().getKing().isUnderCheck()) {
				destination.empty(); // Free the destination
				takeSquare(position); // and come back to the initial position
				System.out.println("King is under check");
				return false;
			}

			if (destination.isOccupied()) { // If this move capture opponent piece
				Piece takenPiece = destination.getPiece();
				takenPiece.removeCheck(table); // Remove the check of this piece on the table.
				Team opponent = takenPiece.getTeam();
				opponent.getPieceList().remove(takenPiece); // And remove this piece on the opponent team;
			}
			return true;
		} else {
			return false;
		}
	}

	private boolean validMove(Square position, Square destination, Table table, Team team) {
		if (RookMove.validMove(position, destination, table, team)) {
			return true;
		} else if (BishopMove.validMove(position, destination, table, team)) {
			return true;
		} else {
			return false;
		}
	}

	private void takeSquare(Square destination) {
		this.queen.setPosition(destination); // Change the current position to the destination
		destination.takenBy(this.queen); // Alarm the square that it is taken by this piece
	}
}