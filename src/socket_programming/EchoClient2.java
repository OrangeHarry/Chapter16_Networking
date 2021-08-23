package socket_programming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

//Ŭ���̾�Ʈ�� ������ �޽����� ����
public class EchoClient2 {
	public static void main(String[] args) throws IOException, IOException {

		Socket socket = new Socket("192.168.0.101", 7254); // local host�� �ĵ� �ȴ�.
		System.out.println("�������� �Ϸ�");

		// ������ ���� (����������)��Ʈ��
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);

		// ������ �о���� ��Ʈ��
		InputStream in = socket.getInputStream();
		DataInputStream dis = new DataInputStream(in);

		Scanner sc = new Scanner(System.in);
		
		System.out.println("������ ������ �޽����� �Է����ּ���.");
		while (true) {
			System.out.print("> ");
			String sendMsg = sc.nextLine();
			dos.writeUTF(sendMsg);
			dos.flush();
			
			String receiveMsg = dis.readUTF();
			System.out.print("�������� : "+receiveMsg);

			if (sendMsg.equalsIgnoreCase("exit")) {
				break;
			}
		}

		dis.close();
		dos.close();
		sc.close();
		socket.close();
		System.out.println("Ŭ���̾�Ʈ ����");
	}
}
//cmd�� ��������