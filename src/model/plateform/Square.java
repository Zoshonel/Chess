package model.plateform;

import model.piece.Piece;

public class Square {
	private final int rowNumber;
	private final int columnNumber;
	private final String color;
	private boolean occupied;
	private boolean underCheck;
	private Piece piece;

	public Square(int rowNumber, int columnNumber, String color) {
		this.rowNumber = rowNumber;
		this.columnNumber = columnNumber;
		this.color = color;
	}

	/**
	 * @return the row
	 */
	public int getRowNumber() {
		return this.rowNumber;
	}

	/**
	 * @return the column
	 */
	public int getColumnNumber() {
		return this.columnNumber;
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
	 * @return the underCheck
	 */
	public boolean isUnderCheck() {
		return this.underCheck;
	}

	/**
	 * @return the piece
	 */
	public Piece getPiece() {
		return this.piece;
	}

	public void empty() {
		this.occupied = false;
		this.piece = null;
	}

	public void takenBy(Piece piece) {
		this.occupied = true;
		this.piece = piece;
	}
}