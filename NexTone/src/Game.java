import java.io.IOException;
import java.util.ArrayList;

public class Game {
	int turn;
	Player player1;
	Player player2;
	static GetValue get = new GetValue();
	

	Game() throws IOException {
		player1 = dekSelect();
		player1.gameSetting();
		player2 = dekSelect();
		player2.gameSetting();
		player1.enemy = player2;
		player2.enemy = player1;
	}

	private static Player dekSelect() throws IOException {
		Player player = new Player();
		Dek d = new Dek();
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

	void startTurn(GetValue get, Player player) {
			printAll(player);
			int num = -2;
			while (num != -1) {
				System.out.print("카드 번호를 입력해주세요: ");
				num = get.Int() - 1;
				if (num==-1){
					break;
				}
				player.useCard(num);
				printAll(player);
			}
			startTurn(get, player.enemy);
		}

	private void printAll(Player player) {
		player.newTurn();
		newLine();
		
		System.out.println(String.format("<%s님의 차례>", player.name));
		System.out.println(String.format("상대방은 핸드에 카드를 %d장 들고 있습니다.",player.enemy.hand.size()));
		newLine();
		
		player.enemy.printField();
		newLine();
		player.printAvailable();
		newLine();
	}

	private void newLine() {
		System.out.println();
	}
}
