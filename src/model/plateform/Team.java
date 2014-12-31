package model.plateform;

import java.util.List;

import model.piece.King;
import model.piece.Piece;

public class Team {
	private final String color;
	private final Table table;
	private boolean myTurn;
	private Team opponent;
	private List<Piece> pieceList;

	public Team(String color, Table table, Boolean myTurn) {
		this.color = color;
		this.table = table;
		initialize();
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return this.color;
	}

	/**
	 * @return the table
	 */
	public Table getTable() {
		return this.table;
	}

	/**
	 * @return the pieceList
	 */
	public List<Piece> getPieceList() {
		return this.pieceList;
	}

	/**
	 * @return the myTurn
	 */
	public boolean isMyTurn() {
		return this.myTurn;
	}

	/**
	 * @return the opponent
	 */
	public Team getOpponent() {
		return this.opponent;
	}

	/**
	 * @param opponent the opponent to set
	 */
	public void setOpponent(Team opponent) {
		this.opponent = opponent;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.color == null) ? 0 : this.color.hashCode());
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
		Team other = (Team) obj;
		if (this.color == null) {
			if (other.color != null) {
				return false;
			}
		} else if (!this.color.equals(other.color)) {
			return false;
		}
		return true;
	}

	private void initialize() {

	}

	public King getKing() {
		return null;
	}

	public void play() {
		this.myTurn = false;
	}
}