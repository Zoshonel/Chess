package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.plateform.Table;
import model.plateform.Team;

public class Controller implements Runnable {
	@Override
	public void run() {
		Table table = new Table();
		Team white = new Team("white", table, true);
		Team black = new Team("black", table, true);
		white.setOpponent(black);
		black.setOpponent(white);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				while (true) {
					System.out.println("White pick piece row : ");
					int a = Integer.parseInt(br.readLine());
					System.out.println("White pick piece column : ");
					int b = Integer.parseInt(br.readLine());
					System.out.println("White pick destination row : ");
					int c = Integer.parseInt(br.readLine());
					System.out.println("White pick destination column : ");
					int d = Integer.parseInt(br.readLine());
					if (white.play(table.getSquare(a, b), table.getSquare(c, d))) {
						break;
					}
				}

				while (true) {
					System.out.println("Black pick piece row : ");
					int e = Integer.parseInt(br.readLine());
					System.out.println("Black pick piece column : ");
					int f = Integer.parseInt(br.readLine());
					System.out.println("Black pick destination row : ");
					int g = Integer.parseInt(br.readLine());
					System.out.println("Black pick destination column : ");
					int h = Integer.parseInt(br.readLine());
					if (black.play(table.getSquare(e, f), table.getSquare(g, h))) {
						break;
					}
				}
			} catch (NumberFormatException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}