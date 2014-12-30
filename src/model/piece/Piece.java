package model.piece;

import model.piece.moveInterface.IMove;
import model.plateform.Square;
import model.plateform.Table;
import model.plateform.Team;

public class Piece {
	protected final Team team;
	protected Square position;
	protected IMove iMove;

	public Piece(Team team, Square position) {
		this.team = team;
		this.position = position;
	}

	/**
	 * @return the color
	 */
	public Team getTeam() {
		return this.team;
	}

	/**
	 * @return the position
	 */
	public Square getPosition() {
		return this.position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Square position) {
		this.position = position;
	}

	public boolean move(Square destination, Table table) {
		return this.iMove.move(destination, table);
	}
}