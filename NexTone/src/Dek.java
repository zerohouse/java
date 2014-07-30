import java.util.ArrayList;
import java.util.Scanner;

public class Dek {

	static final String SEPERATOR = "@%@";
	static final String SEP = "#";
	private String cardstring; // cardStrInDek
	private ArrayList<String> cards;
	private Card[] defaultcards;
	private String[] dek;
	private int pick;

	public Dek(Scanner s, GetValue get) {
		loadDefaultCards();
		getDekList();
		showDekList();
		System.out.print("덱을 선택해주세요: ");
		pick = get.Int(s) - 1;
		selectDek();
		showDek();

	}

	void loadDefaultCards() {
		FileIO f = new FileIO();
		String c = f.readFile("cards.nx");
		String[] eachcard = c.split("\n");
		Card[] defaultcards = new Card[eachcard.length];

		String[] tmp = new String[7];

		for (int i = 0; i < eachcard.length - 1; i++) {
			Card tmpcard = new Card();
			defaultcards[i] = tmpcard;

			tmp = eachcard[i].split(SEPERATOR);

			defaultcards[i].id = i + 1;
			defaultcards[i].name = tmp[1];
			defaultcards[i].cost = Integer.parseInt(tmp[2]);
			defaultcards[i].attack = Integer.parseInt(tmp[3]);
			defaultcards[i].defense = Integer.parseInt(tmp[4]);
			defaultcards[i].type = Integer.parseInt(tmp[5]);
			defaultcards[i].description = tmp[6];
		}
		this.defaultcards = defaultcards;
	}

	void getDekList() {
		FileIO f = new FileIO();
		String deklist = f.readFile("dek.nx");
		dek = deklist.split(SEP);
	}

	void saveDekList() {
		FileIO f = new FileIO();
		String filename = "dek.nx";
		String res = "\n";
		for (int i = 0; i < dek.length - 1; i++) {
			res += dek[i] + SEP;
		}
		res += dek[dek.length - 1];
		f.saveFile(filename, res, false);
	}

	void saveCardsinDek() {
		FileIO f = new FileIO();
		String filename = "./dek/Dek" + (pick + 1) + ".nx";
		cardstring = "";
		for (int i = 0; i < cards.size() - 1; i++) {
			cardstring += cards.get(i) + "@%@";
		}
		f.saveFile(filename, "\n" + cardstring, false);
	}

	void showDekList() {
		int i = 0;
		for (String s : dek) {
			i++;
			System.out.println(String.format("%d. %s", i, s));
		}
	}

	void selectDek() {
		FileIO f = new FileIO();
		cardstring = f.readFile(String.format("./dek/Dek%d.nx", pick + 1));
		String[] tmp = cardstring.split(SEPERATOR);
		cards = new ArrayList<String>();
		for (int i = 0; i < tmp.length; i++) {
			cards.add(tmp[i]);
		}
	}

	void showDek() {
		System.out.println("\n\r <" + cards.get(0) + "의 카드목록>");
		for (int i = 1; i < cards.size() - 1; i++) {
			String[] tmp = cards.get(i).split(SEP);
			System.out.print("(" + (i) + ") ");

			System.out.println(String.format("%s[%d](%d/%d) %s장",
					defaultcards[Integer.parseInt(tmp[0])].name,
					defaultcards[Integer.parseInt(tmp[0])].cost,
					defaultcards[Integer.parseInt(tmp[0])].attack,
					defaultcards[Integer.parseInt(tmp[0])].defense, tmp[1]));

		}
		System.out.println();
	}

	void showCards() {
		for (int i = 0; i < defaultcards.length - 1; i++) {
			System.out.println(String.format("%d. %s[%d](%d/%d)",
					defaultcards[i].id, defaultcards[i].name,
					defaultcards[i].cost, defaultcards[i].attack,
					defaultcards[i].defense));
		}
	}

	void addCard(int add) {
		String[] tmp;
		boolean is = false;

		for (int i = 1; i < cards.size() - 1; i++) {
			tmp = cards.get(i).split(SEP);
			if (Integer.parseInt(tmp[0]) == add) {
				int added = Integer.parseInt(tmp[1]) + 1;
				cards.set(i, tmp[0] + SEP + added);
				is = true;
			}
		}

		if (is == false) {
			System.out.println("AD");
			cards.remove(cards.size() - 1);
			cards.add(add + SEP + 1);
			cards.add("");
		}
	}

	void delCard(int del) {
		String[] tmp = cards.get(del).split(SEP);
		int tmp2 = Integer.parseInt(tmp[1]) - 1;
		if (tmp2 == 0) {
			cards.remove(del);
		} else {
			cards.set(del, tmp[0] + SEP + tmp2);
		}
	}

	public void reName(String dekname) {
		String origin[] = cardstring.split(SEPERATOR, 2);
		dek[pick] = dekname;
		cards.set(0, dekname);
		cardstring = dekname + SEPERATOR + origin[1];
	}
}
