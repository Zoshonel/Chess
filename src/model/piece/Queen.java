package model.piece;

import model.piece.check.QueenCheck;
import model.piece.move.QueenMove;
import model.plateform.Square;
import model.plateform.Team;

public class Queen extends Piece {
	public Queen(Team team, Square position) {
		super(team, position);
		this.iMove = new QueenMove(this);
		this.iCheck = new QueenCheck(this);
	}
}