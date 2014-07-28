import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Card {
	int id, attack, defense, ability, cost;
	String name, description;

	Card(int id) {
		this.id = id;
	}

	void readCards() {
		String cards = readFile();

		String[] eachcard = cards.split("\n");
		for (String s : eachcard) {
			String[] tmp = s.split("@%@");
			try {
				System.out.println("No:" + tmp[0] + " " + tmp[1] + "[" + tmp[2]
						+ "]" + "(" + tmp[3] + "/" + tmp[4] + "/" + tmp[5]
						+ ")" + ">" + tmp[6]);
			} catch (Exception e) {

			}
		}

	}

	String readFile() {
		String res = "";
		try {
			File f = new File("cards.txt");

			BufferedReader in = new BufferedReader(new FileReader(f));
			while (in.readLine() != null) {
				res += in.readLine() + "\n";
			}
			in.close();
		} catch (IOException e) {
			System.err.println(e); // 에러가 있다면 메시지 출력
			System.exit(1);
		}
		return res;

	}

	void addCard() {
		try {
			String s = id + "@%@" + name + "@%@" + cost + "@%@" + attack + "@%@" + defense
					+ "@%@" + ability + "@%@" + description + "\n\r";
			System.out.println(name + "카드가 추가되었습니다.");

			File file = new File("cards.txt");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getName(), true);
			BufferedWriter bufferWritter = new BufferedWriter(fw);
			bufferWritter.write(s);
			bufferWritter.close();

			System.out.println("카드를 저장했습니다.");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
