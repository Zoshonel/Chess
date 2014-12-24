package model.plateform;

import java.util.ArrayList;
import java.util.List;

public class Table {
	private final List<Square> listSquare;

	public Table() {
		this.listSquare = new ArrayList<Square>();
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				if ((i + j) % 2 == 0) {
					Square blackSquare = new Square(i, j, "black");
					this.listSquare.add(blackSquare);
				} else {
					Square whiteSquare = new Square(i, i, "white");
					this.listSquare.add(whiteSquare);
				}
			}
		}
	}

	/**
	 * @return the listSquare
	 */
	public List<Square> getListSquare() {
		return this.listSquare;
	}
}