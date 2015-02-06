package model.plateform;

import java.util.ArrayList;
import java.util.List;

import model.piece.Bishop;
import model.piece.King;
import model.piece.Knight;
import model.piece.Pawn;
import model.piece.Piece;
import model.piece.Queen;
import model.piece.Rook;

public class Team {
	private final String color;
	private final Table table;
	private boolean myTurn;
	private Team opponent;
	private final List<Piece> pieceList;

	public Team(String color, Table table, Boolean myTurn) {
		this.color = color;
		this.table = table;
		this.pieceList = new ArrayList<Piece>();
		if (this.color.equalsIgnoreCase("white")) {
			this.myTurn = true;
		} else {
			this.myTurn = false;
		}
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
	 * @param myTurn the myTurn to set
	 */
	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
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
		if (this.color.equalsIgnoreCase("black")) {
			this.pieceList.add(new Rook(this, this.table.getSquare(8, 1)));

			this.pieceList.add(new Rook(this, this.table.getSquare(8, 8)));
			this.pieceList.add(new Knight(this, this.table.getSquare(8, 2)));
			this.pieceList.add(new Knight(this, this.table.getSquare(8, 7)));
			this.pieceList.add(new Bishop(this, this.table.getSquare(8, 3)));
			this.pieceList.add(new Bishop(this, this.table.getSquare(8, 6)));
			this.pieceList.add(new Queen(this, this.table.getSquare(8, 4)));
			this.pieceList.add(new King(this, this.table.getSquare(8, 5)));
			for (int i = 1; i < 9; i++) {
				this.pieceList.add(new Pawn(this, this.table.getSquare(7, i)));
			}
		} else {
			this.pieceList.add(new Rook(this, this.table.getSquare(1, 1)));
			this.pieceList.add(new Rook(this, this.table.getSquare(1, 8)));
			this.pieceList.add(new Knight(this, this.table.getSquare(1, 2)));
			this.pieceList.add(new Knight(this, this.table.getSquare(1, 7)));
			this.pieceList.add(new Bishop(this, this.table.getSquare(1, 3)));
			this.pieceList.add(new Bishop(this, this.table.getSquare(1, 6)));
			this.pieceList.add(new Queen(this, this.table.getSquare(1, 4)));
			this.pieceList.add(new King(this, this.table.getSquare(1, 5)));
			for (int i = 1; i < 9; i++) {
				this.pieceList.add(new Pawn(this, this.table.getSquare(2, i)));
			}
		}
	}

	public King getKing() {
		for (Piece piece : this.pieceList) {
			if (piece.getClass().equals(King.class)) {
				return (King) piece;
			}
		}
		return null;
	}

	public boolean play(Square init, Square destination) {
		if (this.myTurn) {
			boolean move = init.getPiece().move(destination, this.table);
			if (move) {
				this.myTurn = false;
				this.opponent.setMyTurn(true);
				// Replay check for all the piece on the table since a piece change his position
				for (Piece piece : this.opponent.pieceList) {
					piece.check(this.table);
				}
				for (Piece piece : this.pieceList) {
					piece.check(this.table);
				}
			} else {
				return false;
			}
		}
		return true;
	}
}