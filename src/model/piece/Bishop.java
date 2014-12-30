package model.piece;

import model.piece.moveInterface.BishopMove;
import model.plateform.Square;
import model.plateform.Team;

public class Bishop extends Piece {
	public Bishop(Team team, Square position) {
		super(team, position);
		this.iMove = new BishopMove(this);
	}
}