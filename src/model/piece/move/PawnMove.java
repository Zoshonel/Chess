package model.piece.move;

import model.piece.Pawn;
import model.piece.Piece;
import model.plateform.Square;
import model.plateform.Table;
import model.plateform.Team;

public class PawnMove implements IMove {
	private final Pawn pawn;

	public PawnMove(Pawn pawn) {
		this.pawn = pawn;
	}

	@Override
	public boolean move(Square destination, Table table) {
		Square position = this.pawn.getPosition();
		if (position.equals(destination)) {
			return false;
		}
		if (validMove(position, destination, table, this.pawn.getTeam())) { // If this move is valid
			position.empty(); // Empty the current square
			this.pawn.removeCheck(table);
			takeSquare(destination); // Move to the destination

			// Below is to verify if this move will let the king under check by opponent
			for (Piece piece : this.pawn.getTeam().getOpponent().getPieceList()) {
				piece.removeCheck(table); // Refresh the table
				piece.check(table); // Replay the check for each of opponent piece
			}
			if (this.pawn.getTeam().getKing().isUnderCheck()) { // In case the king is under check
				destination.empty(); // Free the destination
				takeSquare(position); // and come back to the initial position
				System.out.println("King is under check");
				return false;
			} else if (this.pawn.isFirstMove()) { // If everything is fine
				this.pawn.setFirstMove(false);
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
		if (team.getColor() == "white") {
			if (destination.getRowNumber() <= position.getRowNumber()) { // Move backward or horizontally is forbidden
				System.out.println("Pawn can't move backward or horizontally");
				return false;
			}
			if (this.pawn.isFirstMove()) { // Pawn's first move could go up to 2 squares
				if ((destination.getRowNumber() - position.getRowNumber()) > 2) { // So, any move higher than 2 squares is forbidden
					System.out.println("Pawn's first move limited to 2 squares");
					return false;
				}
				if ((destination.getRowNumber() - position.getRowNumber()) == 2) { // In case pawn move 2 squares :
					if (destination.getColumnNumber() != position.getColumnNumber()) { // Pawn have to move straight ahead
						System.out.println("Pawn have to move straight ahead");
						return false;
					}
					if (destination.isOccupied()) { // And the destination must not be occupied
						System.out.println("Destination is occupied");
						return false;
					}
					if (table.getSquare(position.getRowNumber() + 1, position.getColumnNumber()).isOccupied()) { // Or if the square ahead is occupied, pawn can't move.
						System.out.println("Path for pawn is blocked");
						return false;
					}
				}
				if ((destination.getRowNumber() - position.getRowNumber()) == 1) { // In case pawn moves 1 square : we investigate the possibility of column change (pawn changes column only when it capture opponent).
					if (destination.getColumnNumber() == position.getColumnNumber()) { // In case it moves 1 square ahead, not capture opponent :
						if (destination.isOccupied()) { // We check if the column is occupied
							System.out.println("Destination is occupied");
							return false;
						}
						return true;
					}
					if (Math.abs(destination.getColumnNumber() - position.getColumnNumber()) > 1) { // His destination can't be further than 1 colum comparing to the initial position.
						System.out.println("Pawn have to move straight ahead");
						return false;
					}
					if (!destination.isOccupied()) {
						System.out.println("Pawn have to move straight ahead");
						return false;
					} else {
						if (destination.getPiece().getTeam().equals(team)) {
							System.out.println("Pawn can't capture piece in same team");
							return false;
						} else {
							return true;
						}
					}
				}
			} else {
				if ((destination.getRowNumber() - position.getRowNumber()) > 1) {
					System.out.println("Pawn can only move 1 square");
					return false;
				}
				if (destination.getColumnNumber() == position.getColumnNumber()) { // In case it moves 1 square ahead, not capture opponent :
					if (destination.isOccupied()) { // We check if the column is occupied
						System.out.println("Destination is occupied");
						return false;
					} else {
						return true;
					}
				}
				if (Math.abs(destination.getColumnNumber() - position.getColumnNumber()) > 1) { // His destination can't be further than 1 colum comparing to the initial position.
					System.out.println("Pawn have to move straight ahead");
					return false;
				}
				if (!destination.isOccupied()) {
					System.out.println("Destination is occupied");
					return false;
				} else {
					if (destination.getPiece().getTeam().equals(team)) {
						System.out.println("Pawn can't capture piece in same team");
						return false;
					} else {
						return true;
					}
				}
			}
		} else { // If the piece is black, we reverse the substract destination - initial position
			if (destination.getRowNumber() >= position.getRowNumber()) { // Move backward or horizontally is forbidden
				System.out.println("Pawn can't move backward or horizontally");
				return false;
			}
			if (this.pawn.isFirstMove()) { // Pawn's first move could go up to 2 squares
				if ((position.getRowNumber() - destination.getRowNumber()) > 2) { // So, any move higher than 2 squares is forbidden
					System.out.println("Pawn's first move limited to 2 squares");
					return false;
				}
				if ((position.getRowNumber() - destination.getRowNumber()) == 2) { // In case pawn move 2 squares :
					if (destination.getColumnNumber() != position.getColumnNumber()) { // Pawn have to move straight ahead
						System.out.println("Pawn have to move straight ahead");
						return false;
					}
					if (destination.isOccupied()) { // And the destination must not be occupied
						System.out.println("Destination is occupied");
						return false;
					}
					if (table.getSquare(position.getRowNumber() - 1, position.getColumnNumber()).isOccupied()) { // Or if the square ahead is occupied, pawn can't move.
						System.out.println("Path for pawn is blocked");
						return false;
					}
				}
				if ((position.getRowNumber() - destination.getRowNumber()) == 1) { // In case pawn moves 1 square : we investigate the possibility of column change (pawn changes column only when it capture opponent).
					if (destination.getColumnNumber() == position.getColumnNumber()) { // In case it moves 1 square ahead, not capture opponent :
						if (destination.isOccupied()) { // We check if the column is occupied
							System.out.println("Destination is occupied");
							return false;
						}
					}
					if (Math.abs(destination.getColumnNumber() - position.getColumnNumber()) > 1) { // His destination can't be further than 1 colum comparing to the initial position.
						System.out.println("Pawn have to move straight ahead");
						return false;
					}
					if (!destination.isOccupied()) {
						return true;
					} else {
						if (destination.getPiece().getTeam().equals(team)) {
							System.out.println("Pawn can't capture piece in same team");
							return false;
						} else {
							return true;
						}
					}
				}
			} else {
				if ((position.getRowNumber() - destination.getRowNumber()) > 1) {
					System.out.println("Pawn can only move 1 square");
					return false;
				}
				if (destination.getColumnNumber() == position.getColumnNumber()) { // In case it moves 1 square ahead, not capture opponent :
					if (destination.isOccupied()) { // We check if the column is occupied
						System.out.println("Destination is occupied");
						return false;
					} else {
						return true;
					}
				}
				if (Math.abs(destination.getColumnNumber() - position.getColumnNumber()) > 1) { // His destination can't be further than 1 colum comparing to the initial position.
					System.out.println("Pawn have to move straight ahead");
					return false;
				}
				if (!destination.isOccupied()) {
					System.out.println("Pawn have to move straight ahead");
					return false;
				} else {
					if (destination.getPiece().getTeam().equals(team)) {
						System.out.println("Pawn can't capture piece in same team");
						return false;
					} else {
						return true;
					}
				}
			}
		}
		return true;
	}

	private void takeSquare(Square destination) {
		this.pawn.setPosition(destination); // Change the current position to the destination
		destination.takenBy(this.pawn); // Alarm the square that it is taken by this piece
	}
}