package model.piece;

import model.piece.check.KingCheck;
import model.piece.move.KingMove;
import model.plateform.Square;
import model.plateform.Team;

public class King extends Piece {
	private boolean firstMove;

	public King(Team team, Square position) {
		super(team, position);
		this.firstMove = true;
		this.iMove = new KingMove(this);
		this.iCheck = new KingCheck(this);
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

	/**
	 * @return the underCheck
	 */
	public boolean isUnderCheck() {
		if (this.position.underCheckedBy(this.team.getOpponent())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isCheckMated() {
		return false;
	}
}