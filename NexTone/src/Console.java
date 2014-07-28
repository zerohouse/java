import java.util.Scanner;

public class Console {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		GetValue get = new GetValue();

		System.out.println("Welcome to Nextone");
		System.out.println("1. 게임을 시작한다.");
		System.out.println("2. 덱을 구성한다.");
		System.out.println("3. 카드를 만든다.");
		int type = get.Int(scan);
		if (type == 1) {
			System.out.println("게임을 시작합니다.");
		} else if (type == 2) {
			System.out.println("덱을 구성합니다.");
		} else if (type == 3) {
			int id = 0;

			Card c = new Card(id);
			id++;

			System.out.println("카드를 만듭니다.");

			System.out.print("이름? ");
			String name = scan.next();
			c.name = name;
			
			System.out.print("공격력? ");
			int attack = get.Int(scan);
			c.attack = attack;
			
			System.out.print("방어력? ");
			int defense = get.Int(scan);
			c.defense = defense;
			
			System.out.print("특수능력? ");
			int ability = get.Int(scan);
			c.ability = ability;
			
			
			System.out.print("카드설명? ");
			String description = scan.next();
			c.description = description;

			c.addCard();

		} else {
			System.out.println("종료합니다.");
		}

	}

}
