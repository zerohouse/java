import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class initSetting extends Thread {
	Socket socket;
	DataOutputStream out;

	initSetting(Socket socket) {
		this.socket = socket;
		try {
			out = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
		}
	}

	@Override
	public void run() {
		
		System.out.println("게임을 시작합니다.");
		Game client = new Game();
		String initsetting="";
		try {
			initsetting = 0+"@##"+client.multiGame();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			out.writeUTF(initsetting);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
