import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receiver extends Thread {
	
	
	static final String SEPERATOR = "@%@";
	static final String SEP = "#";
	static final String SEPS = "::";
	
	String initstring;
	Socket socket;
	DataInputStream in;
	boolean init;

	
	Receiver(Socket socket) {
		this.socket = socket;
		try {
			init = false;
			in = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
		}
	}

	@Override
	public void run() {
		while (in != null) {
			try {
				if (init == false) {
					stringToSetting(in.readUTF());
					init = true;
				}
				System.out.println(in.readUTF());
			} catch (IOException e) {
			}
		}
	}

	public void stringToSetting(String readUTF) {
		this.initstring = readUTF;		
	}
}