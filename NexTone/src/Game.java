import java.io.IOException;
import java.util.ArrayList;

public class Game {

	static final String SEPS = "::";

	int turn;
	Player me;
	Player you;
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

}
