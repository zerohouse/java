import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Card {
	int id, attack, defense, ability;
	String name, description;

	Card(int id) {
		this.id = id;
	}
	
	
	void readCard(){
		
	}

	void addCard() {
		try {
			
			String s = id + "||" + name + "||" + attack + "||" + defense + "||"
					+ ability + "||" + description + "//" + "\n";
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
