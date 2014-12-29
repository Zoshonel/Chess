package model.piece;

import model.plateform.Square;
import model.plateform.Table;

public class Piece {
	protected String color;
	protected Square position;

	public Piece(String color, Square position) {
		this.color = color;
		this.position = position;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return this.color;
	}

	/**
	 * @return the position
	 */
	public Square getPosition() {
		return this.position;
	}

	public boolean move(Square destination, Table table) {
		if (this.position.equals(destination)) {
			return false;
		}
		if (validMove(destination, table)) {
			this.position.empty();
			takeSquare(destination);
			return true;
		} else {
			return false;
		}
	}

	protected boolean validMove(Square destination, Table table) {
		return true;
	}

	protected void takeSquare(Square destination) {
		this.position = destination;
		destination.takenBy(this);
	}
}