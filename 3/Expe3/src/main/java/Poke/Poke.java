package Poke;
import java.util.Random;

public class Poke {
	public static String[]	createCard(int number) {
		String[] pokeSet = new String[number * 54];
		String[] suit = {"黑桃","红桃","方块","梅花"};
		String[] face = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 13; j++){
				pokeSet[i * 13 + j] = suit[i] + face[j];
			}
		}
		pokeSet[52] = "小王";
		pokeSet[53] = "大王";
		for(int i = 1; i < number; i++){
			for(int j = 0; j < 54; j++) {
				pokeSet[i * 54 + j] = pokeSet[j];
			}
		}
		return pokeSet;
	}
	
	public static void display(String[] cards) {
		int length = cards.length; //num = card.length;
		/*while(i < 4) {
			System.out.format("玩家%d的牌组：", i + 1);
			while(j < 13)
			System.out.format("%s\t", cards[i * 13 + j]);
			System.out.println();*/
		System.out.format("所有的牌：（共%d张）\n", length);	
		for(int i = 0; i < length; i++) {
			System.out.format("%s\t", cards[i]);
			if(i % 10 == 9)
				System.out.printf("\n");
		}
	}
	
	public static void shuffle(String[] cards) {
		Random rand = new Random();
		int len = cards.length;
		for(int i = 0; i < len; i++){
			int place = rand.nextInt(len);
			String temp = cards[i];
			cards[i] = cards[place];
			cards[place] = temp;
		}
		System.out.println("洗牌完毕。");
	}
	
	public static void distribute(String[] cards, int player) {
		int len = cards.length;
		System.out.format("%d张扑克牌分配给%d位玩家，下面是分配情况：\n", len, player);
		for(int i = 0; i < player; i++) {
			System.out.println("玩家" + (i + 1) + "的牌组：");
			int j = 0;
			while((j * player + i) < len) {
				int place = j * player + i;
				System.out.format("%s\t", cards[place]);
				if(place % 9 == 8)	System.out.println();
				j++;
			}
			System.out.println();
		}
		System.out.println("分配完成。");
	}
}
