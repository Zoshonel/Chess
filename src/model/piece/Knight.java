package model.piece;

import model.piece.move.KnightMove;
import model.plateform.Square;
import model.plateform.Team;

public class Knight extends Piece {
	public Knight(Team team, Square position) {
		super(team, position);
		this.iMove = new KnightMove(this);
	}
}