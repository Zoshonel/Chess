package model.piece;

import model.plateform.Square;
import model.plateform.Table;
import model.plateform.Team;

public class Rook extends Piece {
	private boolean firstMove;

	public Rook(Team team, Square position) {
		super(team, position);
		this.firstMove = true;
	}

	@Override
	public boolean move(Square destination, Table table) {
		this.firstMove = false;
		return super.move(destination, table);
	}

	@Override
	protected boolean validMove(Square destination, Table table) {
		if ((destination.getColumnNumber() != this.getPosition().getColumnNumber()) && (destination.getRowNumber() != this.getPosition().getRowNumber())) { // Move to another row and column is forbidden for rook
			return false;
		} else if (pathBlocked(destination, table)) { // Verify if there have no occupied square in his path
			return false;
		} else if (destination.getPiece().getTeam().equals(this.team)) { // Rook can't capture piece with same color as itself
			return false;
		}
		return true;
	}

	private boolean pathBlocked(Square destination, Table table) {
		if (destination.getRowNumber() == this.position.getRowNumber()) { // Rook move horizontally
			int rowNumber = this.position.getRowNumber();
			if (this.position.getColumnNumber() > destination.getColumnNumber()) { // Move right to left
				for (int i = destination.getColumnNumber() + 1; i < this.position.getColumnNumber(); i++) {
					if (table.getSquare(rowNumber, i).isOccupied()) {
						return true;
					}
				}
			} else { // Move left to right : same
				for (int i = this.position.getColumnNumber() + 1; i < destination.getColumnNumber(); i++) {
					if (table.getSquare(rowNumber, i).isOccupied()) {
						return true;
					}
				}
			}
		} else if (destination.getColumnNumber() == this.position.getColumnNumber()) { // Rook move vertically
			int columnNumber = this.position.getColumnNumber();
			if (this.position.getRowNumber() > destination.getRowNumber()) { // Move down
				for (int i = destination.getRowNumber() + 1; i < this.position.getRowNumber(); i++) {
					if (table.getSquare(i, columnNumber).isOccupied()) {
						return true;
					}
				}
			} else { // Move up
				for (int i = this.position.getRowNumber() + 1; i < destination.getRowNumber(); i++) {
					if (table.getSquare(i, columnNumber).isOccupied()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * @return the firstMove
	 */
	public boolean isFirstMove() {
		return this.firstMove;
	}

	/**
	 * @param firstMove the firstMove to set
	 */
	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}
}