package model.piece;

import model.plateform.Square;

public interface Move {
	public void move(Square destination);

	public boolean validMove(Square destination);
}
