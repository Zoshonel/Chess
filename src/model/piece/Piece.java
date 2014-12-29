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