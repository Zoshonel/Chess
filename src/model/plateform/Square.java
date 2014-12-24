package model.plateform;

import model.piece.Piece;

public class Square {
	private final int row;
	private final int column;
	private final String color;
	private boolean occupied;
	private Piece piece;

	public Square(int row, int column, String color) {
		this.row = row;
		this.column = column;
		this.color = color;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return this.row;
	}

	/**
	 * @return the column
	 */
	public int getColumn() {
		return this.column;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return this.color;
	}

	/**
	 * @return the occupied
	 */
	public boolean isOccupied() {
		return this.occupied;
	}

	/**
	 * @return the piece
	 */
	public Piece getPiece() {
		return this.piece;
	}
}