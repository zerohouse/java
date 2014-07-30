public class Dek {

	static final String SEPERATOR = "@%@";
	static final String SEP = "#";
	String cardsindek;
	Card[] defaultcards;
	String[] dek;	
	


	Card[] loadDefaultCards() {
		FileIO f = new FileIO();
		String c = f.readFile("cards.nx");
		String[] eachcard = c.split("\n");
		Card[] cards = new Card[eachcard.length];

		String[] tmp = new String[7];

		for (int i = 0; i < eachcard.length - 1; i++) {
			Card tmpcard = new Card();
			cards[i] = tmpcard;

			tmp = eachcard[i].split(SEPERATOR);

			cards[i].id = i + 1;
			cards[i].name = tmp[1];
			cards[i].cost = Integer.parseInt(tmp[2]);
			cards[i].attack = Integer.parseInt(tmp[3]);
			cards[i].defense = Integer.parseInt(tmp[4]);
			cards[i].type = Integer.parseInt(tmp[5]);
			cards[i].description = tmp[6];
		}
		return cards;
	}

	String[] getDekList() {
		FileIO f = new FileIO();
		String cards = f.readFile("dek.nx");
		String[] dek = cards.split(SEP);
		return dek;
	}
	
	void saveDekList(String[] dek) {
		FileIO f = new FileIO();
		String filename = "dek.nx";
		String res = "\n";
		for (int i = 0; i<dek.length-1;i++){
			res += dek[i]+SEP;
		}
		res +=dek[dek.length-1];
		f.saveFile(filename, res, false);
	}
	
	void saveDek(int pick, String cardsindek) {
		FileIO f = new FileIO();
		String filename = "./dek/Dek" + (pick + 1) + ".nx";
		f.saveFile(filename, "\n" + cardsindek, false);
	}

	void showDekList(String[] dek) {
		int i = 0;
		for (String s : dek) {
			String[] tmp = s.split(SEPERATOR);
			i++;
			System.out.println(String.format("%d. %s", i, tmp[1]));
		}
	}

	String select(int select) {
		String dek[] = getDekList();
		String[] splt = dek[select].split(SEPERATOR);
		return splt[0];
	}

	String SelectDek(int selected, Card[] defaultcards) {
		String filename = select(selected);
		FileIO f = new FileIO();
		String select = f.readFile(String.format("./dek/%s.nx", filename));

		return select;
	}

	void showDek(String res, Card[] defaultcards) {
		String[] sdek = res.split(SEPERATOR);

		System.out.println("\n\r <" + sdek[0] + "의 카드목록>");

		for (int i = 2; i < sdek.length - 1; i++) {
			if (!sdek[i].isEmpty()){
			String[] tmp = sdek[i].split(SEP);
			System.out.print("(" + (i - 1) + ") ");

			System.out.println(String.format("%s[%d](%d/%d) %s장",
					defaultcards[Integer.parseInt(tmp[0])].name,
					defaultcards[Integer.parseInt(tmp[0])].cost,
					defaultcards[Integer.parseInt(tmp[0])].attack,
					defaultcards[Integer.parseInt(tmp[0])].defense, tmp[1]));
			}

		}
		System.out.println();
	}

	void showCards(Card[] cards) {
		for (int i = 0; i < cards.length - 1; i++) {
			System.out.println(String.format("%d. %s[%d](%d/%d)", cards[i].id,
					cards[i].name, cards[i].cost, cards[i].attack,
					cards[i].defense));
		}

	}

	String addCard(int add, String cardsindek) {

		String origin[] = cardsindek.split(SEPERATOR);
		String[] tmp;
		String res = "";
		boolean is = false;

		for (int i = 2; i < origin.length - 1; i++) {

			tmp = origin[i].split(SEP);

			if (Integer.parseInt(tmp[0]) == add - 1) {
				int added = Integer.parseInt(tmp[1]) + 1;
				res += SEPERATOR + tmp[0] + SEP + added;
				is = true;
			} else {
				res += SEPERATOR + origin[i];
			}
		}

		if (is == false) {
			res = SEPERATOR + (add - 1) + "#1" + res;
		}

		res = origin[0] + SEPERATOR + origin[1] + res + SEPERATOR;

		return res;

	}

	String delCard(int del, String cardsindek) {

		String origin[] = cardsindek.split(SEPERATOR);
		String[] tmp;
		String res = "";

		for (int i = 2; i < origin.length; i++) {

			tmp = origin[i].split(SEP);

			if (i == del + 1) {
				int added = Integer.parseInt(tmp[1]) - 1;
				if (added != 0) {
					res += SEPERATOR + tmp[0] + SEP + added;
				}
			} else {
				res += SEPERATOR + origin[i];
			}
		}

		res = origin[0] + SEPERATOR + origin[1] + res + SEPERATOR;

		return res;

	}

	public String reName(String dekname, String cardsindek) {
		String origin[] = cardsindek.split(SEPERATOR,2);
		return dekname+SEPERATOR+origin[1];
	}
	

}
