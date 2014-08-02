import java.util.ArrayList;

public class Dek {

	static final String SEPERATOR = "@%@";
	static final String SEP = "#";
	private String cardstring; // cardStrInDek
	private ArrayList<String> cards; // (0) = cards 갯수

	private CardInfo[] defaultcards;
	private String[] dek;
	public int pick;

	public Dek() {
		loadDefaultCards();
		getDekList();
	}

	void loadDefaultCards() {
		FileIO f = new FileIO();
		String c = f.readFile("cards.nx");
		String[] eachcard = c.split("\n");
		CardInfo[] defaultcards = new CardInfo[eachcard.length];

		String[] tmp = new String[7];

		for (int i = 0; i < eachcard.length - 1; i++) {
			CardInfo tmpcard = new CardInfo();
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
		dek = deklist.split(SEPERATOR);
	}

	void saveDek() {
		FileIO f = new FileIO();
		String filename = "dek.nx";
		String res = "\n";
		for (int i = 0; i < dek.length - 1; i++) {
			res += dek[i] + SEPERATOR;
		}
		f.saveFile(filename, res, false);
	}

	void showDekList() {
		int number = 0;
		String[] dekinfo;
		for (int i = 0; i < dek.length - 1; i++) {
			number++;
			dekinfo = dek[i].split(SEP);
			System.out.println(String.format("%d. %s(%s/30)", number,
					dekinfo[0], dekinfo[1]));
		}
	}

	ArrayList<Integer> showDekWithAvavility() {
		int number = 0;
		ArrayList<Integer> able = new ArrayList<Integer>();
		String avavility;
		String[] dekinfo;
		for (int i = 0; i < dek.length - 1; i++) {
			number++;
			avavility = "미완성";
			dekinfo = dek[i].split(SEP);
			if (Integer.parseInt(dekinfo[1]) == 30) {
				avavility = "사용가능";
				able.add(i);
			}
			System.out.println(String.format("%d. %s(%s/30) %s", number,
					dekinfo[0], dekinfo[1], avavility));
		}
		return able;
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
		String dekinfo[] = dek[pick].split(SEP);
		System.out.println(String.format("\n\r <%s의 카드목록 (%s/30)>", dekinfo[0],
				dekinfo[1]));
		for (int i = 1; i < cards.size() - 1; i++) {
			String[] tmp = cards.get(i).split(SEP);
			System.out.print("(" + (i) + ") ");
			printCardByID(Integer.parseInt(tmp[0]));
			System.out.println(String.format(" %s장", tmp[1]));
		}
		System.out.println();
	}

	private void printCardByID(int id) {
		System.out.print(String.format("%s[%d](%d/%d)", defaultcards[id].name,
				defaultcards[id].cost, defaultcards[id].attack,
				defaultcards[id].defense));
	}

	void showCards() {
		for (int i = 0; i < defaultcards.length - 1; i++) {
			printCard(i);
		}
	}

	private void printCard(int i) {
		System.out.println(String.format("%d. %s[%d](%d/%d)",
				defaultcards[i].id, defaultcards[i].name, defaultcards[i].cost,
				defaultcards[i].attack, defaultcards[i].defense));
	}

	void addCard(int add) {
		boolean is = false;

		if (!addable()) {
			System.out.println("30장이상 넣을 수 없습니다.");
			return;
		}

		if (1 > add || add + 2 > defaultcards.length) {
			System.out.println("없는 번호입니다.");
			return;
		}

		for (int i = 1; i < cards.size() - 1; i++) {
			String[] tmp;
			tmp = cards.get(i).split(SEP);
			if (Integer.parseInt(tmp[0]) == add) {
				int count = Integer.parseInt(tmp[1]);
				if (count > 1) {
					System.out.println("한 카드는 2장까지만 넣을 수 있습니다.");
					return;
				} else {
					cards.set(i, tmp[0] + SEP + 2);
				}
				is = true;
			}
		}

		if (is == false) {
			System.out.println("AD");
			cards.remove(cards.size() - 1);
			cards.add(add + SEP + 1);
			cards.add("");
		}
		cardCount(1);
	}

	private boolean addable() {
		boolean addable = false;
		String[] tmp;
		tmp = dek[pick].split(SEP);
		if (Integer.parseInt(tmp[1]) < 30) {
			addable = true;
		}
		return addable;
	}

	void delCard(int del) {
		String[] tmp = cards.get(del).split(SEP);
		int tmp2 = Integer.parseInt(tmp[1]) - 1;
		cardCount(-1);
		if (tmp2 == 0) {
			cards.remove(del);
			return;
		}
		cards.set(del, tmp[0] + SEP + tmp2);
	}

	void cardCount(int amount) {
		String[] tmp;
		tmp = dek[pick].split(SEP);
		int cardscnt = Integer.parseInt(tmp[1]) + amount;
		dek[pick] = tmp[0] + SEP + cardscnt;
		saveDek();
	}

	public void reName(String dekname) {
		String origin[] = cardstring.split(SEPERATOR, 2);
		String[] dekinfo = dek[pick].split(SEP);
		dek[pick] = dekname + SEP + dekinfo[1];
		cardstring = dekname + SEPERATOR + origin[1];
	}

	void infoToCard(Player player) {
		int attack, defense, cost, index = 0;

		String name, description;
		Card card;
		String dekinfo[] = dek[pick].split(SEP);
		System.out.println(String.format("\n\r <%s의 카드목록 (%s/30)>", dekinfo[0],
				dekinfo[1]));
		for (int i = 1; i < cards.size() - 1; i++) {
			String[] tmp = cards.get(i).split(SEP);
			System.out.print("(" + (i) + ") ");
			printCardByID(Integer.parseInt(tmp[0]));

			attack = defaultcards[Integer.parseInt(tmp[0])].attack;
			defense = defaultcards[Integer.parseInt(tmp[0])].defense;
			cost = defaultcards[Integer.parseInt(tmp[0])].cost;
			name = defaultcards[Integer.parseInt(tmp[0])].name;
			description = defaultcards[Integer.parseInt(tmp[0])].description;

			System.out.println(String.format(" %s장", tmp[1]));

			for (int j = 0; j < Integer.parseInt(tmp[1]); j++) {
				card = new Card(attack, defense, cost, player);
				card.name = name;
				card.description = description;
				card.index = index;
				index++;
				player.dek.add(card);
			}

		}
		System.out.println();
	}

}
