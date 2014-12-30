package model.plateform;

import java.util.Set;

import model.piece.Piece;

public class Square {
	private final int rowNumber;
	private final int columnNumber;
	private final String color;
	private boolean occupied;
	private Set<Team> checkedBy;
	private final Piece[] piece;

	public Square(int rowNumber, int columnNumber, String color) {
		this.rowNumber = rowNumber;
		this.columnNumber = columnNumber;
		this.color = color;
		this.piece = new Piece[1];
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
	 * @param occupied the occupied to set
	 */
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	/**
	 * @return the checkedBy
	 */
	public Set<Team> getCheckedBy() {
		return this.checkedBy;
	}

	/**
	 * @return the piece
	 */
	public Piece getPiece() {
		return this.piece[0];
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.color == null) ? 0 : this.color.hashCode());
		result = prime * result + this.columnNumber;
		result = prime * result + this.rowNumber;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Square other = (Square) obj;
		if (this.color == null) {
			if (other.color != null) {
				return false;
			}
		} else if (!this.color.equals(other.color)) {
			return false;
		}
		if (this.columnNumber != other.columnNumber) {
			return false;
		}
		if (this.rowNumber != other.rowNumber) {
			return false;
		}
		return true;
	}

	public void empty() {
		setOccupied(false);
		this.piece[0] = null;
	}

	public void takenBy(Piece piece) {
		setOccupied(true);
		this.piece[0] = piece;
	}
}