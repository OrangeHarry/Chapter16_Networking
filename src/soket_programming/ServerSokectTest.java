package soket_programming;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSokectTest {
	public static void main(String[] args) {

		try {
			ServerSocket serverSocket = new ServerSocket(7762);// ���Ƿ� �־��ذ�(�׷��� 1024 ���� ���ڸ� ���� �ȵ�)

			System.out.println("Ŭ���̾�Ʈ ���� �����...");
			Socket clientSocket = serverSocket.accept();
			                                   //������ ������ �޾��ִ� ������ �ϳ� ���� �ΰ��� ������ �Ƿ� �� �������ִ� �޼ҵ��� �����ϸ� �ȴ�.
			System.out.println("����Ǿ����ϴ�." + serverSocket);
			
			clientSocket.close();
			serverSocket.close();

		} catch (IOException e) {
//			e.printStackTrace();
		}
	}
}
//sokect = ����� ��ü, ������ ������ �������ش�(��ī�� ���������ΰ���) 
// �� ī��