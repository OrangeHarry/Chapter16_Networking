package socket_programming;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SimpleClient2 {
	public static void main(String[] args) throws IOException, IOException {

		Socket socket = new Socket("192.168.0.101", 8000);
		System.out.println("�������� �Ϸ�");

		InputStream in = socket.getInputStream();
		DataInputStream dis = new DataInputStream(in);

		String message = dis.readUTF();
		System.out.println("�����޼��� : " + message);

		dis.close();
		socket.close();
		System.out.println("Ŭ���̾�Ʈ ����");
	}
}
//cmd���� ipconfig�� ġ�� ���� IP�ּҸ� �˼��ִ�.
//cmd���� ping google.com �� ġ�� �� ��Ʈ��ũ���°� �� �Ǵ��� Ȯ���̰����ϴ�