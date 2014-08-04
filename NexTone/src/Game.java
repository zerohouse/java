import java.io.IOException;
import java.util.ArrayList;

public class Game {

	static final String SEPS = "::";

	int turn;
	Player me;
	Player you;
	Player Turn;
	static GetValue get = new GetValue();

	public void singleGame() throws IOException {
		me = dekSelect();
		me.gameSetting();
		you = dekSelect();
		you.gameSetting();
		me.enemy = you;
		you.enemy = me;
	}

	public String multiGame() throws IOException {
		me = new Player();
		me.GetPlayerName();
		Dek d = new Dek();
		d.getDekList();
		ArrayList<Integer> able = new ArrayList<Integer>();
		able = d.showDekWithAvavility();
		System.out.print(me.name + "님의 덱을 선택해 주세요: ");
		d.pick = get.Dek(able);
		String cardstring = d.selectDek();
		d.infoToCard(me);
		System.out.println("Press Enter to Start");
		System.in.read();
		String handanddek = me.gameSetting();
		return me.name + SEPS + cardstring + SEPS + handanddek;
	}

	private static Player dekSelect() throws IOException {
		Player player = new Player();
		System.out.println();
		System.out.println();
		System.out.println();
		player.GetPlayerName();
		Dek d = new Dek();
		d.getDekList();
		ArrayList<Integer> able = new ArrayList<Integer>();
		able = d.showDekWithAvavility();
		System.out.print(player.name + "님의 덱을 선택해 주세요: ");
		d.pick = get.Dek(able);
		d.selectDek();
		d.infoToCard(player);
		System.out.println("Press Enter to Start");
		System.in.read();
		return player;
	}

	void startTurn(Player player) {
		printAll(player);
		int num = -2;
		while (num != -1) {
			System.out.print("카드 번호를 입력해주세요: ");
			num = get.Int() - 1;
			if (num == -1) {
				break;
			}
			player.useCard(num);
			printAll(player);
		}
		startTurn(player.enemy);
	}

	void startTurn() {
		printMulti();
		int num = -2;
		if (me.myturn) {
			while (num != -1) {
				System.out.print("카드 번호를 입력해주세요: ");
				num = get.Int() - 1;
				if (num == -1) {
					break;
				}
				me.useCard(num);
				printAll(me);
			}
			me.myturn = false;
			you.myturn = true;
		}
	}

	private void printAll(Player player) {
		player.newTurn();
		newLine();

		System.out.println(String.format("<%s님의 차례>", player.name));
		System.out.println(String.format("상대방은 핸드에 카드를 %d장 들고 있습니다.",
				player.enemy.hand.size()));
		newLine();

		player.enemy.printField();
		newLine();
		player.printAvailable();
		newLine();
	}
	
	private void printMulti() {
		
		Player whosturn = new Player();
		if (me.myturn){
			whosturn = me;
			me.newTurn();
		}
		else if (you.myturn){
			whosturn = you;
			you.newTurn();
		}
		
		newLine();
		
		System.out.println(String.format("<%s님의 차례>", whosturn.name));
		System.out.println(String.format("상대방은 핸드에 카드를 %d장 들고 있습니다.",
				me.enemy.hand.size()));
		newLine();

		me.enemy.printField();
		newLine();
		me.printAvailable();
		newLine();
	}
	

	private void newLine() {
		System.out.println();
	}

}
