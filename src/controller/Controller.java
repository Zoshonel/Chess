package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.plateform.Table;
import model.plateform.Team;

public class Controller implements Runnable {
	Team white;
	Team black;
	Table table;

	@Override
	public void run() {
		initialize();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean gameEnded = false;
		while (!gameEnded) { // Loop until the game ends
			try {
				while (true) { // Loop until white play a correct move
					System.out.println("White pick piece row : ");
					int a = Integer.parseInt(br.readLine());
					System.out.println("White pick piece column : ");
					int b = Integer.parseInt(br.readLine());
					if (this.table.getSquare(a, b).isOccupied()) {
						if (this.table.getSquare(a, b).getPiece().getTeam().getColor().equalsIgnoreCase("white")) {
							System.out.println("White pick destination row : ");
							int c = Integer.parseInt(br.readLine());
							System.out.println("White pick destination column : ");
							int d = Integer.parseInt(br.readLine());

							if (this.white.play(this.table.getSquare(a, b), this.table.getSquare(c, d))) {
								if (this.black.getKing().isCheckMated()) { // After a correct move by white, if black king got checkmated, the game will be finished
									gameEnded = true;
								}
								break;
							}
						} else {
							System.out.println("Pick a white piece, not a black one");
						}
					} else {
						System.out.println("This square is empty");
					}
				}
				if (!gameEnded) { // Black could only play when the game is not finished yet
					while (true) { // Loop until black play a correct move
						System.out.println("Black pick piece row : ");
						int e = Integer.parseInt(br.readLine());
						System.out.println("Black pick piece column : ");
						int f = Integer.parseInt(br.readLine());
						if (this.table.getSquare(e, f).isOccupied()) {
							if (this.table.getSquare(e, f).getPiece().getTeam().getColor().equalsIgnoreCase("black")) {
								System.out.println("Black pick destination row : ");
								int g = Integer.parseInt(br.readLine());
								System.out.println("Black pick destination column : ");
								int h = Integer.parseInt(br.readLine());
								if (this.black.play(this.table.getSquare(e, f), this.table.getSquare(g, h))) {
									if (this.white.getKing().isCheckMated()) { // After a correct move by black, if white king got checkmate, the game will be finished
										gameEnded = true;
									}
									break;
								}
							} else {
								System.out.println("Pick a black piece, not a white one");
							}
						} else {
							System.out.println("This square is empty");
						}
						;
					}
				}
			} catch (NumberFormatException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void initialize() {
		this.table = new Table();
		this.white = new Team("white", this.table, true);
		this.black = new Team("black", this.table, true);
		this.white.setOpponent(this.black);
		this.black.setOpponent(this.white);
	}
}