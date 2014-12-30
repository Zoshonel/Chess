package model.piece;

import model.piece.move.RookMove;
import model.plateform.Square;
import model.plateform.Team;

public class Rook extends Piece {
	private boolean firstMove;

	public Rook(Team team, Square position) {
		super(team, position);
		this.firstMove = true;
		this.iMove = new RookMove(this);
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