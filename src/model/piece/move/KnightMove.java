package model.piece.move;

import model.piece.Knight;
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
			if (this.knight.getTeam().getKing().isUnderCheck()) { // If the move let the king be checked
				position.takenBy(this.knight); // Cancel the move, re-take the initial square
				return false;
			}
			this.knight.removeCheck(table);
			takeSquare(destination);
			this.knight.check(table);
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