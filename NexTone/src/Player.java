import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Player {
	int mana, maxmana;
	Card king;
	Player enemy;
	ArrayList<Card> field;
	Stack<Card> dummy; // 카드 더미
	ArrayList<Card> dek; // 전체 카드(초기화용도)
	ArrayList<Card> hand; // 손에든 카드
	ArrayList<Card> mycards; // 사용 가능한 전체 카드
	Random random = new Random();

	Player() {
		dek = new ArrayList<Card>();
		field = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		dummy = new Stack<Card>();

		makeKing();
	}

	private void makeKing() {
		king = new Card(0, 30, 0);
		king.name = "Pl1";
		king.attackable = 0;
		king.maxattackable = 0;
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
		int i = 0;
		System.out.println("<Field>");
		for (Card card : field) {
			i++;
			System.out.println(String.format("(%d) %s (%d/%d) - %d/%d", i,
					card.name, card.attack, card.defense, card.attackable,
					card.maxattackable));
		}
		System.out.println("<Hand>");
		for (Card card : hand) {
			i++;
			System.out.println(String.format("(%d) %s[%d] (%d/%d)", i,
					card.name, card.cost, card.attack, card.defense));
		}
		System.out.println(String.format("마나(%d/%d)", mana, maxmana));
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

	public void gameSetting(GetValue get) {
		cardToHand(4);
		printArrayList(hand);
		System.out.print("바꾸실카드를 입력해주세요: ");
		changeFirstCard(get.IntArray());
		printArrayList(hand);
		dekToDummy();
	}

	public void dekToDummy() {
		for (Card card : hand) {
			removeCardByindex(card.index, dek);
		}
		int size = dek.size();
		int ran;
		Card card;

		for (int i = 0; i < size - 1; i++) {
			ran = random.nextInt(dek.size() - 1);
			card = dek.get(ran);
			dummy.push(card);
			dek.remove(ran);
		}
		dummy.push(dek.get(0));
	}

	public void useCard(int num) {
		if (num < field.size()) {
			useFieldCard(num);
			return;
		}
		useHandCard(num - field.size());
	}

	private void useFieldCard(int num) {

	}

	private void useHandCard(int num) {
		if (hand.get(num).cost > mana) {
			System.err.println("마나가 부족합니다.");
			return;
		}
		mana -= hand.get(num).cost;
		hand.get(num).maxattackable = 1;
		field.add(hand.get(num));
		hand.remove(num);
	}
}
