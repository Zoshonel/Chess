package model.piece.moveInterface;

import model.plateform.Square;
import model.plateform.Table;

public interface IMove {
	public boolean move(Square destination, Table table);
}