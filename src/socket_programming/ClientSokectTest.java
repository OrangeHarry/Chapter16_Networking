package socket_programming;

import java.io.IOException;
import java.net.Socket;

public class ClientSokectTest {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 7762);
			System.out.println("연결되었습니다." + socket);
			socket.close();

		} catch (IOException e) {
//			e.printStackTrace();
		}
	}
}
