
public class CardInfo {
	
	static final String SEPERATOR = "@%@";
	int id, attack, defense, type, cost;
	String name, description;

	
	void readCards() {
		FileIO f = new FileIO();
		String cards = f.readFile("cards.nx");

		String[] eachcard = cards.split("\n");
		for (String s : eachcard) {
			String[] tmp = s.split(SEPERATOR);

			try {
				System.out.println(String.format(
						"%d. %s[%s] (%s/%s) Type:%s - %s",
						(Integer.parseInt(tmp[0]) + 1), tmp[1], tmp[2], tmp[3],
						tmp[4], tmp[5], tmp[6]));

				id = Integer.parseInt(tmp[0]) + 1;
			} catch (Exception e) {
			}

		}

	}

	void addCard() {
		try {
			String s = id + SEPERATOR + name + SEPERATOR + cost + SEPERATOR
					+ attack + SEPERATOR + defense + SEPERATOR + type
					+ SEPERATOR + description + "\n\r";
			System.out.println(name + "카드가 추가되었습니다.");

			FileIO f = new FileIO();

			f.saveFile("cards.nx", s, true);
			System.out.println("저장했습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
