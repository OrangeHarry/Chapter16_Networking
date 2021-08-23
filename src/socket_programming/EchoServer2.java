package socket_programming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer2 {
	public static void main(String[] args) throws IOException {

		ServerSocket server = new ServerSocket(7254);
		System.out.println("���� �غ� �Ϸ�");

		Socket socket = server.accept();
		System.out.println("Ŭ���̾�Ʈ ����Ϸ�");
		// Ŭ���̾�Ʈ IP�ּ�
		System.out.println(socket.getInetAddress());
		
		//�о���� ��Ʈ��
		InputStream in = socket.getInputStream();
		DataInputStream dis = new DataInputStream(in);
		
		//���� ��Ʈ��
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out); 

		while (true) {
			String userMessage = dis.readUTF();
			System.out.println("����� �޽��� : " + userMessage);

			if (userMessage.equalsIgnoreCase("exit")) {
				break;
			}
			
			//���� �޽����� �ٽ� ����
			dos.writeUTF(userMessage);
			dos.flush();
		}
		dis.close();
		in.close();
		dos.close();

		socket.close();
		server.close();

		System.out.println("��������");
	}
}
//cmd�� ��������