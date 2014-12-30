package model.piece.move;

import model.plateform.Square;
import model.plateform.Table;

public interface IMove {
	public boolean move(Square destination, Table table);
}