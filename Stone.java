package digitoy_games_assigment;

import java.util.ArrayList;

public class Stone {

	public static Player players = new Player();

	public Stone(Player players) {
		this.players = players;
	}

	private static int getRnd(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max. value must be greater then min value");
		}
		int rnd = (int) (Math.random() * ((max - min) + 1)) + min;
		return rnd;
	}

	static int getjoker() {
		int joker = 0;
		int rnd = getRnd(1, 51);
		if (rnd == 12 || rnd == 25 || rnd == 38 || rnd == 51) {
			joker = rnd - 12;
		} else {
			joker = rnd + 1;
		}
		return rnd;
	}

	public static ArrayList getOList(int sayi) {
		if (sayi == 0)
			return players.getPlayer0();
		else if (sayi == 1)
			return players.getPlayer1();
		else if (sayi == 2)
			return players.getPlayer2();
		else if (sayi == 3)
			return players.getPlayer3();
		else
			return null;
	}

	public static void distributeStone(ArrayList taslist) {
		int rnd = getRnd(0, 3);
		int j = 0;
		rnd = 0;
		for (int i = 0; i < 4; i++) {// oyuncular
			ArrayList olist = getOList(i);
			if (i == rnd) {
				for (int s = j; s < j + 15; s++) {
					olist.add(taslist.get(s));
				}
				j = j + 15;
			} else {
				for (int s = j; s < j + 14; s++) {
					olist.add(taslist.get(s));
				}
				j = j + 14;
			}
		}
	}
}
