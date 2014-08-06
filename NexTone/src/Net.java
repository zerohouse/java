import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Net {

	public String askServer() throws UnknownHostException, IOException {

		System.out.println("서버에 연결중입니다.");
		Socket socket = new Socket("10.73.43.180", 12423);

		InputStream in = socket.getInputStream();
		DataInputStream datain = new DataInputStream(in);
		String response = datain.readUTF();
		System.out.println("서버 정보 : " + response);

		datain.close();
		socket.close();
		
		return response;
	}

	public void asServer() throws IOException {
		ServerSocket serverSocket = null;
		Socket socket = null;

		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("참여자를 기다립니다.");

			socket = serverSocket.accept();
			Server server = new Server(socket);
			server.start();
		} catch (Exception e) {
		}
	}

	public void asClient(String ip) {
		try {
			Socket socket = new Socket(ip, 7777);
			Client client = new Client(socket);
			client.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
