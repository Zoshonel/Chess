package model.piece.move;

import model.piece.Knight;
import model.piece.Piece;
import model.plateform.Square;
import model.plateform.Table;
import model.plateform.Team;

public class KnightMove implements IMove {
	private final Knight knight;

	public KnightMove(Knight knight) {
		this.knight = knight;
	}

	@Override
	public boolean move(Square destination, Table table) {
		Square position = this.knight.getPosition();
		if (position.equals(destination)) {
			return false;
		}
		if (validMove(position, destination, table, this.knight.getTeam())) {
			position.empty(); // Empty the current square
			this.knight.removeCheck(table);
			takeSquare(destination); // Move to the destination

			// Below is to verify if this move will let the king under check by opponent
			for (Piece piece : this.knight.getTeam().getOpponent().getPieceList()) {
				piece.removeCheck(table); // Refresh the table
				piece.check(table); // Replay the check for each of opponent piece
			}

			if (this.knight.getTeam().getKing().isUnderCheck()) { // If the move let the king be checked
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

	protected boolean validMove(Square position, Square destination, Table table, Team team) {
		int deltaColumn = destination.getColumnNumber() - position.getColumnNumber();
		int deltaRow = destination.getRowNumber() - position.getRowNumber();
		int x = deltaColumn * deltaColumn + deltaRow * deltaRow;
		if (x != 5) {
			return false;
		}
		if (destination.isOccupied()) {
			if (destination.getPiece().getTeam().equals(team)) { // Knight can't capture piece with same color as itself
				return false;
			}
		}
		return true;
	}

	private void takeSquare(Square destination) {
		this.knight.setPosition(destination); // Change the current position to the destination
		destination.takenBy(this.knight); // Alarm the square that it is taken by this piece
	}
}