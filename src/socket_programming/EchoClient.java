package socket_programming;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

//클라이언트가 서버로 메시지를 보내
public class EchoClient {
	public static void main(String[] args) throws IOException, IOException {

		Socket socket = new Socket("192.168.0.101", 8000); // local host로 쳐도 된다.
		System.out.println("서버연결 완료");

		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);

		Scanner sc = new Scanner(System.in);
		System.out.println("서버로 전송할 메시지를 입력해주세요.");
		while (true) {
			System.out.print("> ");
			String msg = sc.nextLine();
			dos.writeUTF(msg);
			dos.flush();

			if (msg.equalsIgnoreCase("exit")) {
				break;
			}
		}

		// InputStream in = socket.getInputStream();
		// DataInputStream dis = new DataInputStream(in);

		dos.close();
		sc.close();
		socket.close();
		System.out.println("클라이언트 종료");
	}
}
//cmd로 실행하자