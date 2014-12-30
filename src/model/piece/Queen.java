package model.piece;

import model.plateform.Square;
import model.plateform.Table;
import model.plateform.Team;

public class Queen extends Piece {
	public Queen(Team team, Square position) {
		super(team, position);
	}

	@Override
	public boolean validMove(Square destination, Table table) {
		return false;
	}
}