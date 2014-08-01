import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Player {
	int mana, maxmana;
	Card king;
	Player enemy;
	ArrayList<Card> mycards;
	Stack<Card> dummy;
	ArrayList<Card> dek;
	ArrayList<Card> hand;
	Random random = new Random();

	Player() {
		dek = new ArrayList<Card>();
		mycards = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		dummy = new Stack<Card>();
		makeKing();
	}

	private void makeKing() {
		king = new Card(0, 30, 0);
		mycards.add(king);
	}

	void newTurn() {
		maxmana++;
		mana = maxmana;
		mycards.add(dummy.pop());
		for (Card card : mycards) {
			card.attackable = card.maxattackable;
		}
	}

	public void removeCard(Card card) {
		mycards.remove(card.index);
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
			removeCardFromArray(i, hand);
		}
	}

	public void removeCardFromArray(int index, ArrayList<Card> cards) {
		for (int i = 1; i < cards.size(); i++) {
			if (cards.get(i).index == index) {
				cards.remove(i-1);
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

	public void printDummy() {
		Card card;
		int size = dummy.size();
		for (int i = 0; i < size; i++) {
			card = dummy.pop();
			System.out.println(String.format("(%d) %s[%d] (%d/%d)", i,
					card.name, card.cost, card.attack, card.defense));
		}
	}

	public void gameStart() {

	}

	public void dekToDummy() {
		for (Card card : hand) {
			removeCardFromArray(card.index, dek);
		}
		int size = dek.size();
		int ran;
		Card card;

		for (int i = 0; i < size-1; i++) {
			ran = random.nextInt(dek.size() - 1);
			card = dek.get(ran);
			dummy.push(card);
			dek.remove(ran);
		}
		dummy.push(dek.get(0));
	}
}
