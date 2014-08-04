import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Sender extends Thread {
	String name;
	Socket socket;
	DataOutputStream out;
	String initinfo;

	Sender(Socket socket, String initinfo) {
		this.socket = socket;
		this.initinfo = initinfo;
		try {
			out = new DataOutputStream(socket.getOutputStream());
			name = "[" + socket.getInetAddress() + ":" + socket.getPort()
					+ "] ";
		} catch (Exception e) {
		}
	}

	@Override
	public void run() {
		GetValue get = new GetValue();

		try {
			out.writeUTF(initinfo);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		while (out != null) {
			try {
				out.writeUTF(Integer.toString(get.Int()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}