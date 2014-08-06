import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Player {

	static final String SEPERATOR = "@%@";
	static final String SEP = "#";
	static final String SEPS = "::";

	int attacker;
	int mana, maxmana;
	Card king;
	String name;
	ArrayList<Card> field;
	Stack<Card> dummy; // 카드 더미
	ArrayList<Card> dek; // 전체 카드(초기화용도)
	ArrayList<Card> hand; // 손에든 카드
	ArrayList<Card> mycards; // 사용 가능한 전체 카드
	Random random = new Random();
	static GetValue get = new GetValue();

	Player enemy;

	Player() {
		dek = new ArrayList<Card>();
		field = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		dummy = new Stack<Card>();
	}

	public void GetPlayerName() {
		System.out.print("새로운 Player의 이름을 입력해주세요: ");
		name = get.String();
		makeKing();
	}

	void makeKing() {
		king = new Card(0, 30, 0, this);
		king.name = name;
		king.attackable = 0;
		king.maxattackable = 0;
		king.defense = 30;
		king.maxdefense = 30;
		king.attackdefault = 0;
		field.add(king);
	}

	void newTurn() {
		maxmana++;
		mana = maxmana;
		hand.add(dummy.pop());
		for (Card card : field) {
			card.attackable = card.maxattackable;
		}
	}

	public void dieCharacter(Card card) {
		field.remove(card.index);
	}

	public void cardToHand(int size) {
		int ran = 0;
		for (int i = 0; i < size; i++) {
			do {
				ran = random.nextInt(29);
			} while (hasSameElement(hand, ran));
			hand.add(dek.get(ran));
		}
	}

	public void cardToHand(String handstring) {
		String[] handstrings = handstring.split(SEP);
		for (String s : handstrings) {
			try {
				hand.add(dek.get(Integer.parseInt(s)));
			} catch (Exception e) {
			}
		}
	}

	public boolean hasSameElement(ArrayList<Card> array, int value) {
		for (Card card : array) {
			if (card.index == value) {
				return true;
			}
		}
		return false;
	}

	public void changeFirstCard(int[] change) {
		cardToHand(change.length);
		int[] index = change;
		for (int i = change.length - 1; i > -1; i--) {
			index[i] = hand.get(change[i]).index;
		}
		for (int i : index) {
			removeCardByindex(i, hand);
		}
	}

	public void removeCardByindex(int index, ArrayList<Card> cards) {
		for (int i = 1; i < cards.size(); i++) {
			if (cards.get(i).index == index) {
				cards.remove(i - 1);
			}
		}
	}

	public void printArrayList(ArrayList<Card> cards) {
		int i = 0;
		for (Card card : cards) {
			i++;
			System.out.println(String.format("(%d) %s[%d] (%d/%d)", i,
					card.name, card.cost, card.attack, card.defense));
		}
	}

	public void printAvailable() {
		int i = printField();

		System.out.println();

		System.out.println("<Hand>");
		for (Card card : hand) {
			i++;
			System.out.println(String.format("(%d) %s[%d] (%d/%d)", i,
					card.name, card.cost, card.attack, card.defense));
		}
		System.out.println(String.format("마나(%d/%d)", mana, maxmana));
	}

	public String availableToString() {
		String result = "";
		int i = 0;
		result += String.format("<%s님의 Field>", name) + "\n";
		for (Card card : field) {
			i++;
			result += String.format("(%d) %s (%d/%d) - %d/%d", i, card.name,
					card.attack, card.defense, card.attackable,
					card.maxattackable)
					+ "\n";
		}

		result += "\n<Hand>\n";
		for (Card card : hand) {
			i++;
			result += String.format("(%d) %s[%d] (%d/%d)", i, card.name,
					card.cost, card.attack, card.defense) + "\n";
		}
		result += String.format("마나(%d/%d)", mana, maxmana) + "\n";
		return result;
	}

	public int printField() {
		int i = 0;
		System.out.println(String.format("<%s님의 Field>", name));
		for (Card card : field) {
			i++;
			System.out.println(String.format("(%d) %s (%d/%d) - %d/%d", i,
					card.name, card.attack, card.defense, card.attackable,
					card.maxattackable));
		}
		return i;
	}

	public String fieldtoString() {
		String result = "";
		int i = 0;
		result += String.format("<%s님의 Field>", name) + "\n";
		for (Card card : field) {
			i++;
			result += String.format("(%d) %s (%d/%d) - %d/%d", i, card.name,
					card.attack, card.defense, card.attackable,
					card.maxattackable)
					+ "\n";
		}
		return result;
	}

	public void printDummy() {
		Card card;
		int size = dummy.size();
		for (int i = 0; i < size; i++) {
			card = dummy.pop();
			System.out.println(String.format("(%d) %s[%d] (%d/%d)", i,
					card.name, card.cost, card.attack, card.defense));
		}
	}

	public String gameSetting() {
		cardToHand(4);
		printArrayList(hand);
		System.out.print("바꾸실카드를 입력해주세요: ");
		changeFirstCard(get.IntArray());
		printArrayList(hand);
		String handstring = handToString();
		String dekstring = dekToDummy();
		return handstring + SEPS + dekstring;
	}

	public String handToString() {
		String res = "";
		for (Card card : hand) {
			res += card.index + SEP;
		}
		return res;
	}

	public String dekToDummy() {
		for (Card card : hand) {
			removeCardByindex(card.index, dek);
		}
		int size = dek.size();
		int ran;
		Card card;
		String randomstring = "";
		for (int i = 0; i < size - 1; i++) {
			ran = random.nextInt(dek.size() - 1);
			randomstring += ran + SEP;
			card = dek.get(ran);
			dummy.push(card);
			dek.remove(ran);
		}
		dummy.push(dek.get(0));
		return randomstring;
	}

	public void dekToDummy(String dummystring) {
		for (Card card : hand) {
			removeCardByindex(card.index, dek);
		}

		String[] dummystrings = dummystring.split(SEP);

		for (String s : dummystrings) {
			try {
				dummy.push(dek.get(Integer.parseInt(s)));
				dek.remove(Integer.parseInt(s));
			} catch (Exception e) {
			}
		}
		dummy.push(dek.get(0));
	}

	public String useCard(int num) {
		String message = "";
		if (num < field.size()) {
			this.attacker = num;
			return "attack";
		}
		num -= field.size();
		if (hand.get(num).cost > mana) {
			message += "마나가 부족합니다.\n";
			return message;
		}
		useHandCard(num);
		return "Done";
	}

	public void attackTarget(int enemycard) {
		field.get(attacker).attackTarget(enemy.field.get(enemycard), attacker, enemycard);
	}

	public void useHandCard(int num) {
		mana -= hand.get(num).cost;
		hand.get(num).maxattackable = 1;
		field.add(hand.get(num));
		hand.remove(num);
	}

	void printAll() {
		newLine();

		System.out.print(String.format("%s님", enemy.name));
		System.out.println(String.format("은 핸드에 카드를 %d장 들고 있습니다.",
				enemy.hand.size()));
		newLine();

		enemy.printField();
		newLine();
		printAvailable();
		newLine();
	}

	String toStringAll() {
		String result = "";
		result += "\n";

		result += String.format("%s님", enemy.name);
		result += String.format("은 핸드에 카드를 %d장 들고 있습니다.", enemy.hand.size())
				+ "\n";
		;
		result += "\n";
		result += enemy.fieldtoString();
		result += "\n";
		result += availableToString();
		result += "\n";
		return result;
	}

	private void newLine() {
		System.out.println();
	}

	void startTurn() {
		newTurn();
		printAll();
		int num = -2;
		while (num != -1) {
			System.out.print("카드 번호를 입력해주세요: ");
			num = get.Int() - 1;
			if (num == -1) {
				break;
			}
			useCard(num);
			printAll();
		}
		enemy.startTurn();
	}

}
