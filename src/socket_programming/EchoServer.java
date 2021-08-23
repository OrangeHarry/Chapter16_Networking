package socket_programming;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static void main(String[] args) throws IOException {

		ServerSocket server = new ServerSocket(8000);
		System.out.println("���� �غ� �Ϸ�");

		Socket socket = server.accept();
		System.out.println("Ŭ���̾�Ʈ ����Ϸ�");
		// Ŭ���̾�Ʈ IP�ּ�
		System.out.println(socket.getInetAddress());

		InputStream in = socket.getInputStream();
		DataInputStream dis = new DataInputStream(in);

		while (true) {
			String userMessage = dis.readUTF();
			System.out.println("����� �޽��� : " + userMessage);

			if (userMessage.equalsIgnoreCase("exit")) {
				break;
			}
		}

		// OutputStream out = socket.getOutputStream();
		// DataOutputStream dos = new DataOutputStream(out); //���븦 �ΰ��� �Ⱦҳ�

		dis.close();
		in.close();

		socket.close();
		server.close();

		System.out.println("��������");
	}
}
//cmd�� ��������