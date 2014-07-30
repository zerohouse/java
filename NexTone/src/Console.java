import java.util.Scanner;

public class Console {

	public static void main(String[] args) throws Exception {

		Scanner scan = new Scanner(System.in);
		GetValue get = new GetValue();
		Card c = new Card();

		System.out.println("Welcome to Nextone");
		System.out.println("1. 게임을 시작한다.");
		System.out.println("2. 덱을 구성한다.");
		System.out.println("3. 카드를 만든다.");
		
		int type = get.Int(scan);
		
		if (type == 1) {
			System.out.println("게임을 시작합니다.");
		} else if (type == 2) {
			dekActivity(scan, get);

		} else if (type == 3) {
			newCard(get, scan, c);

		} else {
			System.out.println("종료합니다.");
		}

	}

	private static void dekActivity(Scanner scan, GetValue get) {
		
		Dek d = new Dek(scan, get);


		while (true) {
			System.out.println("1. 카드 추가하기");
			System.out.println("2. 카드 빼기");
			System.out.println("3. 덱 이름 변경");
			System.out.println("4. 뒤로");

			int deks = get.Int(scan);
			int add, del;
			String dekname;

			if (deks == 1) {

				d.showCards();
				System.out.print("추가할카드를 선택해주세요. 0을 입력하면 종료합니다: ");
				add = get.Int(scan);
				d.addCard(add-1);
				d.saveCardsinDek();
				d.showDek();

			}

			else if (deks == 2) {
				d.showDek();
				System.out.print("삭제할카드를 선택해주세요. 0을 입력하면 종료합니다: ");

				del = get.Int(scan);
				d.delCard(del);

				d.saveCardsinDek();
				d.showDek();
			}

			else if (deks == 3) {
				System.out.print("새로운 이름을 입력해 주세요: ");
				dekname = scan.next();
				d.reName(dekname);
				d.saveCardsinDek();
				d.saveDekList();
				d.showDek();
			}

			else {
				dekActivity(scan, get);
			}
		}
	}

	static void newCard(GetValue get, Scanner scan, Card c) {

		while (true) {

			System.out.println("카드목록 : 이름[코스트] (공/체/타입) > 설명");
			c.readCards();

			System.out.print("이름? ");

			String name = scan.next();
			c.name = name;

			System.out.print("코스트? ");
			int cost = get.Int(scan);
			c.cost = cost;

			System.out.print("공격력? ");
			int attack = get.Int(scan);
			c.attack = attack;

			System.out.print("방어력? ");
			int defense = get.Int(scan);
			c.defense = defense;

			System.out.print("종족? ");
			int type = get.Int(scan);
			c.type = type;

			System.out.print("카드설명? ");
			String description = scan.next();
			c.description = description;

			c.addCard();
		}
	}
}
