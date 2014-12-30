package model.piece;

import model.plateform.Square;
import model.plateform.Table;
import model.plateform.Team;

public class Piece {
	protected Team team;
	protected Square position;

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

	public boolean move(Square destination, Table table) {
		if (this.position.equals(destination)) { // Can't move to the same square
			return false;
		}
		if (validMove(destination, table)) { // If the move is valid
			this.position.empty(); // Leave the current square...
			takeSquare(destination); // ... to take another square
			return true;
		} else {
			return false;
		}
	}

	protected boolean validMove(Square destination, Table table) { // Will be modify in sub-class
		return true;
	}

	protected void takeSquare(Square destination) {
		this.position = destination; // Change the current position to the destination
		destination.takenBy(this); // Alarm the square that it is taken by this piece
	}
}