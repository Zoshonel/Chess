package model.piece;

import model.plateform.Square;

public interface Action {
	public boolean move(Square destination);

	public boolean validMove(Square destination);
}
