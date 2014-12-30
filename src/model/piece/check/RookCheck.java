package model.piece.check;

import model.piece.Rook;
import model.plateform.Table;

public class RookCheck implements ICheck {
	private final Rook rook;

	public RookCheck(Rook roook) {
		this.rook = roook;
	}

	@Override
	public void check(Table table) {

	}
}