package soket_programming;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSokectTest {
	public static void main(String[] args) {

		try {
			ServerSocket serverSocket = new ServerSocket(7762);// 임의로 넣어준값(그래도 1024 밑의 숫자를 쓰면 안됨)

			System.out.println("클라이언트 연결 대기중...");
			Socket clientSocket = serverSocket.accept();
			                                   //소켓이 들어오면 받아주는 소켓을 하나 만들어서 두개의 소켓을 실로 쫙 연결해주는 메소드라고 생각하면 된다.
			System.out.println("연결되었습니다." + serverSocket);
			
			clientSocket.close();
			serverSocket.close();

		} catch (IOException e) {
//			e.printStackTrace();
		}
	}
}
//sokect = 통신의 주체, 서버와 서버를 연결해준다(랜카드 같은느낌인거지) 
// 랜 카드