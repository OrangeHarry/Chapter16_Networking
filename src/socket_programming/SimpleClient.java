package socket_programming;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Arrays;

public class SimpleClient {
	public static void main(String[] args) throws IOException, IOException {

		Socket socket = new Socket("192.168.0.101", 8000);
		System.out.println("서버연결 완료");

		InputStream in = socket.getInputStream();
		byte[] buff = new byte[10];
		
		in.read(buff);
		System.out.println(Arrays.toString(buff));

		in.close();
		socket.close();
		System.out.println("클라이언트 종료");
	}
}
//cmd에서 ipconfig를 치면 나의 IP주소를 알수있다.
//cmd에서 ping google.com 을 치면 내 네트워크상태가 잘 되는지 확인이가능하다