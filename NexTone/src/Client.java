import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends Thread {

	static final String SEPERATOR = "@%@";
	static final String SEP = "#";
	static final String SEPS = "::";

	GetValue get = new GetValue();

	Socket socket;
	DataInputStream in;
	DataOutputStream out;

	Client(Socket socket) {
		this.socket = socket;
		try {
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
		}
	}

	@Override
	public void run() {
		try {
			Game client = new Game();
			String initsetting = client.multiGame();
			out.writeUTF("0@##" + initsetting);
		} catch (IOException e1) {
			e1.printStackTrace();
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
		if (Integer.parseInt(type[0]) == 1) { // 1번이면 그냥 출력
			System.out.println(type[1]);
		} else if (Integer.parseInt(type[0]) == 2) {
			myTurn();
		} else if (Integer.parseInt(type[0]) == 3) {
			useFieldCard();
		} else if (Integer.parseInt(type[0]) == 4) {
			System.err.println(type[1]);
		} else if (Integer.parseInt(type[0]) == 7) {
			chooseTarget(type[1]);
		}
	}

	private void chooseTarget(String field) {
		System.out.println(field);
		System.out.print("공격할 대상을 선택해 주세요: ");
		int target = get.Int()-1;
		try {
			out.writeUTF("7@##"+target);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void useFieldCard() {
		int enemycard = get.Int() - 1;
		try {
			out.writeUTF("3@##" + enemycard);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void myTurn() {
		int num = -2;
		System.out.println("<나의 턴>");
		System.out.print("사용할 카드 번호를 입력해주세요(0:턴넘김) ");
		num = get.Int() - 1;
		if (num == -1) {
			yourTurn();
			return;
		}
		useCard(num);
	}

	private void yourTurn() {
		try {
			out.writeUTF("6@##");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void useCard(int num) {
		try {
			out.writeUTF("5@##" + num);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}