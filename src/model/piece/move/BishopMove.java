package model.piece.move;

import model.piece.Bishop;
import model.piece.Piece;
import model.plateform.Square;
import model.plateform.Table;
import model.plateform.Team;

public class BishopMove implements IMove {
	private final Bishop bishop;

	public BishopMove(Bishop bishop) {
		this.bishop = bishop;
	}

	@Override
	public boolean move(Square destination, Table table) {
		Square position = this.bishop.getPosition();
		if (position.equals(destination)) {
			return false;
		}
		if (validMove(position, destination, table, this.bishop.getTeam())) { // If this move is valid
			position.empty(); // Empty the current square
			this.bishop.removeCheck(table);
			takeSquare(destination); // Move to the destination

			// Below is to verify if this move will let the king under check by opponent
			for (Piece piece : this.bishop.getTeam().getOpponent().getPieceList()) {
				piece.removeCheck(table); // Refrest the table
				piece.check(table); // Replay the check for each of opponent piece
			}

			if (this.bishop.getTeam().getKing().isUnderCheck()) { // If the move let the king be checked
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

	public static boolean validMove(Square position, Square destination, Table table, Team team) {
		if (Math.abs(destination.getColumnNumber() - position.getColumnNumber()) != Math.abs(destination.getRowNumber() - position.getRowNumber())) { // Bishop can only move diagonally
			return false;
		} else if (pathBlocked(position, destination, table)) { // Verify if there have no occupied square in his path
			System.out.println("Path is blocked");
			return false;
		} else if (destination.isOccupied()) {
			if (destination.getPiece().getTeam().equals(team)) { // Bishop can't capture piece with same color as itself
				System.out.println("Can't capture piece in same team");
				return false;
			}
		}
		return true;
	}

	private static boolean pathBlocked(Square position, Square destination, Table table) {
		int delta = Math.abs(destination.getColumnNumber() - position.getColumnNumber());
		if (destination.getRowNumber() > position.getRowNumber()) { // Move upward...
			if (destination.getColumnNumber() > position.getColumnNumber()) { // ...to the right
				for (int i = 1; i < delta; i++) {
					if (table.getSquare(position.getRowNumber() + i, position.getColumnNumber() + i).isOccupied()) {
						return true;
					}
				}
			} else { // ...to the left
				for (int i = 1; i < delta; i++) {
					if (table.getSquare(position.getRowNumber() + i, position.getColumnNumber() - i).isOccupied()) {
						return true;
					}
				}
			}
		} else { // Move downward...
			if (destination.getColumnNumber() > position.getColumnNumber()) { // ...to the right
				for (int i = 1; i < delta; i++) {
					if (table.getSquare(position.getRowNumber() - i, position.getColumnNumber() + i).isOccupied()) {
						return true;
					}
				}
			} else { // ...to the left
				for (int i = 1; i < delta; i++) {
					if (table.getSquare(position.getRowNumber() - i, position.getColumnNumber() - i).isOccupied()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private void takeSquare(Square destination) {
		this.bishop.setPosition(destination); // Change the current position to the destination
		destination.takenBy(this.bishop); // Alarm the square that it is taken by this piece
	}
}