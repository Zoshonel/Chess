package model.piece;

import model.plateform.Square;
import model.plateform.Table;

public abstract class Piece {
	protected String color;
	protected Square position;
	protected Table table;

	public Piece(String color, Square position, Table table) {
		this.color = color;
		this.position = position;
		this.table = table;
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

	/**
	 * @return the table
	 */
	public Table getTable() {
		return this.table;
	}
}