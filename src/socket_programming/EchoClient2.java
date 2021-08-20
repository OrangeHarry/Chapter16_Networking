package socket_programming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

//클라이언트가 서버로 메시지를 보내
public class EchoClient2 {
	public static void main(String[] args) throws IOException, IOException {

		Socket socket = new Socket("192.168.0.80", 7761); // local host로 쳐도 된다.
		System.out.println("서버연결 완료");

		// 데이터 전송 (서버쪽으로)스트림
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);

		// 데이터 읽어오는 스트림
		InputStream in = socket.getInputStream();
		DataInputStream dis = new DataInputStream(in);

		Scanner sc = new Scanner(System.in);
		
		System.out.println("서버로 전송할 메시지를 입력해주세요.");
		while (true) {
			System.out.print("> ");
			String sendMsg = sc.nextLine();
			dos.writeUTF(sendMsg);
			dos.flush();
			
			String receiveMsg = dis.readUTF();
			System.out.print("서버응답 : "+receiveMsg);

			if (sendMsg.equalsIgnoreCase("exit")) {
				break;
			}
		}

		dis.close();
		dos.close();
		sc.close();
		socket.close();
		System.out.println("클라이언트 종료");
	}
}
//cmd로 실행하자