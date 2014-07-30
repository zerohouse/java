import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO {

	String readFile(String filename) {
		String res = "";
		try {
			File f = new File(filename);

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

	void saveFile(String filename, String s, boolean append) {
		try {

			File file = new File(filename);

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(filename, append);
			BufferedWriter bufferWritter = new BufferedWriter(fw);
			bufferWritter.write(s);
			bufferWritter.close();

			System.out.println("저장했습니다.\n\r");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}



}
