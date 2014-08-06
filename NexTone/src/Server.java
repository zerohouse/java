import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Server extends Thread {

	static final String SEPERATOR = "@%@";
	static final String SEP = "#";
	static final String SEPS = "::";

	GetValue get = new GetValue();

	Socket socket;
	DataInputStream in;
	DataOutputStream out;
	int yourcardnum;
	Game game;

	Server(Socket socket) {
		this.socket = socket;
		try {
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
		}
	}

	@Override
	public void run() {

		game = new Game();
		try {
			game.multiGame();

		} catch (IOException e) {
			e.printStackTrace();
		}

		while (in != null) {
			try {
				accept(in.readUTF());
			} catch (IOException e) {
			}
		}
	}

	private void accept(String readUTF) {
		String[] type = readUTF.split("@##"); // type 0 => 초기화
		if (Integer.parseInt(type[0]) == 0) {
			initGame(type[1]);
			myTurn();
		} else if (Integer.parseInt(type[0]) == 5) { // 5는 상대방의 카드 사용
			useYourCard(Integer.parseInt(type[1]));
		} else if (Integer.parseInt(type[0]) == 6) { // 6는 상대방의 턴 종료
			myTurn();
		} else if (Integer.parseInt(type[0]) == 7) {
			game.you.attackTarget(Integer.parseInt(type[1]));
			refreshClient();
			game.me.printAll();
			doYourAction();
		}
	}

	private void useYourCard(int type) {
		String message = game.you.useCard(type);
		game.me.printAll();
		refreshClient();
		if (message.equals("attack")) {
			chooseTarget();
			return;
		}
		if (message.equals("Done")) {
			doYourAction();
			return;
		}
		try {
			out.writeUTF("1@##" + message);
			doYourAction();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void chooseTarget() {
		try {
			String field = game.me.fieldtoString();
			out.writeUTF("7@##" + field); // choose attack target
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doYourAction() {
		try {
			out.writeUTF("2@##");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void myTurn() {
		game.me.newTurn();
		int num = -2;
		game.me.printAll();

		while (num != -1) {
			refreshClient();
			System.out.println("<나의 턴>");
			System.out.print("사용할 카드 번호를 입력해주세요(0:턴넘김) ");
			num = get.Int() - 1;
			if (num == -1) {
				break;
			}
			String result = game.me.useCard(num);
			if (result.equals("attack")) {
				game.you.printField();
				System.out.print("공격할 대상을 선택해 주세요: ");
				int target = get.Int() - 1;
				game.me.attackTarget(target);
				game.me.printAll();
			} else if (result.equals("Done")) {
				game.me.printAll();
			} else {
				System.out.println(result);
			}
		}
		yourTurn();
	}

	private void yourTurn() {
		game.you.newTurn();
		refreshClient();
		doYourAction();
	}

	private void refreshClient() {
		try {
			checkWinner();
			out.writeUTF("1@##" + game.you.toStringAll());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void checkWinner() throws IOException {
		if (game.me.field.size() == 0 && game.you.field.size() == 0) {
			System.out.println("\n\n\n\n\nDraw!");
			out.writeUTF("1@##\n\n\n\n\nDraw!");
		} else if(game.me.field.size()==0){
			out.writeUTF("1@##\n\n\n\n\nYou Win!");
			System.out.println("\n\n\n\n\nYou Lose!");
		} else if(game.you.field.size()==0){
			out.writeUTF("1@##\n\n\n\n\nYou Lose!");
			System.out.println("\n\n\n\n\nYou Win!");
		}
	}

	private void initGame(String initstring) {
		String[] setting = initstring.split("::");
		game.you = new Player();

		game.you.name = setting[0];
		Dek d = new Dek();
		d.selectDek(setting[1]);
		d.infoToCardWithoutPrint(game.you);
		game.you.cardToHand(setting[2]);
		game.you.dekToDummy(setting[3]);
		game.you.makeKing();
		game.you.enemy = game.me;
		game.me.enemy = game.you;
	}

}