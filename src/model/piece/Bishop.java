package model.piece;

import model.piece.check.BishopCheck;
import model.piece.move.BishopMove;
import model.plateform.Square;
import model.plateform.Team;

public class Bishop extends Piece {
	public Bishop(Team team, Square position) {
		super(team, position);
		this.iMove = new BishopMove(this);
		this.iCheck = new BishopCheck(this);
	}
}