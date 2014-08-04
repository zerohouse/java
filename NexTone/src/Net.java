import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Net extends Game {

	Receiver receiver;
	Sender sender;

	public String askServer() throws UnknownHostException, IOException {

		System.out.println("서버에 연결중입니다.");
		Socket socket = new Socket("10.73.38.136", 12423);

		InputStream in = socket.getInputStream();
		DataInputStream datain = new DataInputStream(in);
		String response = datain.readUTF();
		System.out.println("서버 정보 : " + response);

		datain.close();
		socket.close();
		return response;
	}

	public void asServer(String initsetting) throws IOException {
		ServerSocket serverSocket = null;
		Socket socket = null;

		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("참여자를 기다립니다.");

			socket = serverSocket.accept();

			sender = new Sender(socket, initsetting);
			receiver = new Receiver(socket);

			sender.start();
			receiver.start();
		} catch (Exception e) {
		}
	}

	public void asClient(String ip, String initsetting) {
		try {
			Socket socket = new Socket(ip, 7777);

			System.out.println("게임에 연결되었습니다.");
			Sender sender = new Sender(socket, initsetting);
			receiver = new Receiver(socket);

			sender.start();
			receiver.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
