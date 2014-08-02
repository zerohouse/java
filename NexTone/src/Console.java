import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Console {

	public static void main(String[] args) throws Exception {

		GetValue get = new GetValue();
		CardInfo cardinfo = new CardInfo();

		System.out.println("Welcome to Nextone");
		System.out.println("1. 게임을 시작한다.");
		System.out.println("2. 덱을 구성한다.");
		System.out.println("3. 카드를 만든다.");

		int type = get.Int();

		if (type == 1) {
			Game game = new Game();
			
			Player me = dekSelect(get);
			me.gameSetting(get);
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("게임을 시작합니다.");
			while (true) {
				nextTurn(get, me);
			}
		} else if (type == 2) {
			dekActivity(get);

		} else if (type == 3) {
			newCard(get, cardinfo);

		} else {
			System.out.println("종료합니다.");
		}

	}

	private static void nextTurn(GetValue get, Player me) {
		me.newTurn();
		System.out.println("나의 턴");
		System.out.println("<상대의 카드>"); // printEnemySize();
		System.out.println("3장");
		System.out.println("<상대의 필드>");
		// Ememy.printField();
		me.printAvailable();
		int num = -2;
		while (num != -1) {
			System.out.print("카드 번호를 입력해주세요: ");
			num = get.Int() - 1;
			me.useCard(num);
			me.printAvailable();
		}
	}

	private static Player dekSelect(GetValue get) throws IOException {
		System.out.println("게임을 시작합니다.");
		Dek d = new Dek();
		ArrayList<Integer> able = new ArrayList<Integer>();
		able = d.showDekWithAvavility();
		System.out.print("덱을 선택해 주세요: ");
		d.pick = get.Dek(able);
		d.selectDek();
		Player me = new Player();
		d.infoToCard(me);
		System.out.println("Press Enter to Start");
		System.in.read();
		return me;
	}

	private static void dekActivity(GetValue get) {

		Dek d = new Dek();
		d.showDekList();
		System.out.print("덱을 선택해주세요: ");
		d.pick = get.Int() - 1;
		d.selectDek();
		d.showDek();

		while (true) {
			System.out.println("1. 카드 추가하기");
			System.out.println("2. 카드 빼기");
			System.out.println("3. 덱 이름 변경");
			System.out.println("4. 뒤로");

			int choose = get.Int();
			int add, del;
			String dekname;

			if (choose == 1) {
				d.showCards();
				System.out.print("추가할카드를 선택해주세요. 0을 입력하면 종료합니다: ");
				add = get.Int();
				d.addCard(add - 1);
				d.saveCardsinDek();
				d.showDek();

			}

			else if (choose == 2) {
				d.showDek();
				System.out.print("삭제할카드를 선택해주세요. 0을 입력하면 종료합니다: ");
				del = get.Int();
				d.delCard(del);
				d.saveCardsinDek();
				d.showDek();
			}

			else if (choose == 3) {
				System.out.print("새로운 이름을 입력해 주세요: ");
				dekname = get.String();
				d.reName(dekname);
				d.saveCardsinDek();
				d.saveDek();
				d.showDek();
			}

			else {
				dekActivity(get);
			}
		}
	}

	static void newCard(GetValue get, CardInfo c) {

		while (true) {

			System.out.println("카드목록 : 이름[코스트] (공/체/타입) > 설명");
			c.readCards();

			System.out.print("이름? ");

			String name = get.String();
			c.name = name;

			System.out.print("코스트? ");
			int cost = get.Int();
			c.cost = cost;

			System.out.print("공격력? ");
			int attack = get.Int();
			c.attack = attack;

			System.out.print("방어력? ");
			int defense = get.Int();
			c.defense = defense;

			System.out.print("종족? ");
			int type = get.Int();
			c.type = type;

			System.out.print("카드설명? ");
			String description = get.String();
			c.description = description;

			c.addCard();
		}
	}

}
