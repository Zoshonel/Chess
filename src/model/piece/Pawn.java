package model.piece;

import model.piece.move.PawnMove;
import model.plateform.Square;
import model.plateform.Team;

public class Pawn extends Piece {
	private boolean firstMove;

	public Pawn(Team team, Square position) {
		super(team, position);
		this.firstMove = true;
		this.iMove = new PawnMove(this);
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