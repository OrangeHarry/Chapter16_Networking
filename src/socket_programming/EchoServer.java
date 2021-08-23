package socket_programming;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static void main(String[] args) throws IOException {

		ServerSocket server = new ServerSocket(8000);
		System.out.println("서버 준비 완료");

		Socket socket = server.accept();
		System.out.println("클라이언트 연결완료");
		// 클라이언트 IP주소
		System.out.println(socket.getInetAddress());

		InputStream in = socket.getInputStream();
		DataInputStream dis = new DataInputStream(in);

		while (true) {
			String userMessage = dis.readUTF();
			System.out.println("사용자 메시지 : " + userMessage);

			if (userMessage.equalsIgnoreCase("exit")) {
				break;
			}
		}

		// OutputStream out = socket.getOutputStream();
		// DataOutputStream dos = new DataOutputStream(out); //빨대를 두개를 꽂았네

		dis.close();
		in.close();

		socket.close();
		server.close();

		System.out.println("서버종료");
	}
}
//cmd로 실행하자