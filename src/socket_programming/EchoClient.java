package socket_programming;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class EchoClient {
	public static void main(String[] args) throws IOException, IOException {

		Socket socket = new Socket("192.168.0.80", 7761);
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