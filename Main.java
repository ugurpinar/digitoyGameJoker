package digitoy_games_assigment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Main {  
	static Player players = new Player();
	static Stone stone = new Stone(players);
	static ArrayList stoneList = new ArrayList();
	static ArrayList notUsedList = new ArrayList();
	static int joker = 0;
	static int fakejoker = 52;

	public static ArrayList doStoneList() {
		ArrayList list = new ArrayList();
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 53; j++) {
				list.add(j);
			}
		return list;
	}

	public static int calculatePoint(ArrayList list) {
		notUsedList = new ArrayList();
		int point = 0;
		if (list.contains(-10))
			point++;

		ArrayList yellowList = new ArrayList();
		ArrayList blueList = new ArrayList();
		ArrayList blackList = new ArrayList();
		ArrayList redList = new ArrayList();

		for (int i = 0; i < list.size(); i++) {
			if ((int) list.get(i) >= 0 && (int) list.get(i) < 13) {
				yellowList.add(list.get(i));
			} else if ((int) list.get(i) >= 13 && (int) list.get(i) < 26) {
				blueList.add(list.get(i));
			} else if ((int) list.get(i) >= 26 && (int) list.get(i) < 39) {
				blackList.add(list.get(i));
			} else if ((int) list.get(i) >= 39 && (int) list.get(i) < 52) {
				redList.add(list.get(i));
			}
		}
		//rengine göre ayrýlmýþ gruplarýn ,dizi haline gelebilenlerinin puanlarýnýn hesaplanmasý(1,2,3)
		point = point + calculatePointList(yellowList);
		point = point + calculatePointList(blueList);
		point = point + calculatePointList(blackList);
		point = point + calculatePointList(redList);
		//dizi oluþturmayan taþlarýn puanýnýn hesaplanmasý (7,7,7)
		point = point + notUsedListpoint(notUsedList);

		return point;
	}

	public static int calculatePointDouble(ArrayList list) {
		Collections.sort(list);
		int point = 0;

		for (int i = 0; i < list.size(); i++) {
			int temp = i + 1;
			if (i != list.size() - 1 && list.get(i) == list.get(temp))
				point++;
		}
		return point;
	}
	public static int notUsedListpoint(ArrayList list) {
		int pointX = 0;

		for (int i = 0; i < list.size(); i++) {
			if (list.contains((int) list.get(i) + 13)) {
				pointX++;
			}
		}
		return pointX;
	}
	 /**
     * @return Elin herhangi bir renginin puanýný dondurur.
     */
	public static int calculatePointList(ArrayList list) {
		int pointX = 0;
		ArrayList oddList = new ArrayList();//Eldeki çift sayýda olanlarýn ve tek sayýda olanlarýn atýldýðý liste
		ArrayList evenList = new ArrayList();//Eldeki çift sayýda olanlarýn 1 tanesinin atýldýðý liste
		ArrayList usedOddList = new ArrayList();//Ard Arda sýralamada kullanýlanlar
		ArrayList usedEvenList = new ArrayList();
		ArrayList notUsedOddList = new ArrayList();//ard arda sýralama kullanýlmayanlar
		ArrayList notUsedEvenList = new ArrayList();
		/* list = (3,3,4,4,5,5,7,8,11) ==>
		 * oddlist = (3,4,5,7,8,11)
		 * evenlist = (3,4,5)
		 */
		if (list.size() > 1) { 

			for (int j = 0; j < list.size(); j++) {
				int value = (int) list.get(j);
				int notAdd = 0;
				for (int k = j + 1; k < list.size(); k++) {
					if ((int) list.get(k) == value) {
						evenList.add((int) list.get(k));
						notAdd = 1;
					}
				}
				if (notAdd == 0)
					oddList.add((int) list.get(j));
			}

		} else {//ilgili renkten 1 adet var ise yada yoksa sýralama yapýlamaz,diðer renklerle ayný sayýlarý dizmek üzere ayýrýlýr.
			for (int j = 0; j < list.size(); j++) {
				notUsedList.add((int) list.get(j));
			}

		}
		  //ilk listenin puan hesabý
		for (int i = 0; i < oddList.size(); i++) {
			int temp = i + 1;
			if (i != oddList.size() - 1 && ((int) oddList.get(temp) - (int) oddList.get(i) == 1)) {

				if (!usedOddList.contains((int) oddList.get(temp)))
					usedOddList.add((int) oddList.get(temp));
				if (!usedOddList.contains((int) oddList.get(i)))
					usedOddList.add((int) oddList.get(i));
			}
		}

		for (int s = 0; s < oddList.size(); s++) {
			if (!usedOddList.contains(oddList.get(s)))
				notUsedOddList.add(oddList.get(s));
		}
		pointX = pointX + usedOddList.size();
		// 12 = 2 puan , 123 = 3 puan gibi 2 veya fazlýsý sýralanan her taþ için artýrarak puan verilir.
		for (int i = 0; i < evenList.size(); i++) {
			int temp = i + 1;
			if (i != evenList.size() - 1 && ((int) evenList.get(temp) - (int) evenList.get(i) == 1)) {

				if (!usedEvenList.contains((int) evenList.get(temp)))
					usedEvenList.add((int) evenList.get(temp));
				if (!usedEvenList.contains((int) evenList.get(i)))
					usedEvenList.add((int) evenList.get(i));
			}
		}
		//ikinci listenin puan hesabý
		for (int s = 0; s < evenList.size(); s++) {
			if (!usedEvenList.contains(evenList.get(s)))
				notUsedEvenList.add(evenList.get(s));
		}

		pointX = pointX + usedEvenList.size();

		for (int y = 0; y < notUsedOddList.size(); y++) {
			notUsedList.add(notUsedOddList.get(y));
		}
		for (int y = 0; y < notUsedEvenList.size(); y++) {
			notUsedList.add(notUsedEvenList.get(y));
		}
		return pointX;
	}
	 public static void comparePoint(int player1,int player2,int player3,int player4) {
		 int max = player1;
		 
		 if(max<player2)
			 max=player2;
		 if(max<player3)
			 max=player3;
		 if(max<player4)
			 max=player4;
		 
		 if (player1 == max) {
			 System.out.println("Player1 has most chance to win game with point "+max);
		 }
		 if (player2 == max) {
			 System.out.println("Player2 has most chance to win game with point "+max);
		 }
		 if (player3 == max) {
			 System.out.println("Player3 has most chance to win game with point "+max);
		 }
		 if (player4 == max) {
			 System.out.println("Player4 has most chance to win game with point "+max);
		 }
	 }
	 public static void calcPoints() {
		 	
		 //Her oyuncu için eli çifte gidecek ve gitmeyecek þekilde ayrý ayrý puan hesaplanarak yüksek olaný seçilir.
		 	int max0 = 0;
			int point0 = calculatePoint(players.getPlayer0());
			int point0Double = calculatePointDouble(players.getPlayer0());
			if (point0 > point0Double)
				max0 = point0;
			else
				max0 = point0Double;

			int max1 = 0;
			int point1 = calculatePoint(players.getPlayer1());
			int point1Double = calculatePointDouble(players.getPlayer1());
			if (point1 > point1Double)
				max1 = point1;
			else
				max1 = point1Double;

			int max2 = 0;
			int point2 = calculatePoint(players.getPlayer2());
			int point2Double = calculatePointDouble(players.getPlayer2());
			if (point2 > point2Double)
				max2 = point2;
			else
				max2 = point2Double;

			int max3 = 0;
			int point3 = calculatePoint(players.getPlayer3());
			int point3Double = calculatePointDouble(players.getPlayer3());
			if (point3 > point3Double)
				max3 = point3;
			else
				max3 = point3Double;
		
			comparePoint(max0,max1,max2,max3);
	 }
	public static void main(String[] args) {
		stoneList = doStoneList();
		Collections.shuffle(stoneList);
		joker = stone.getjoker();
		stone.distributeStone(stoneList);
		calcPoints();
	}
}
