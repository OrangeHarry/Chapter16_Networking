package socket_programming;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

//Ŭ���̾�Ʈ�� ������ �޽����� ����
public class EchoClient {
	public static void main(String[] args) throws IOException, IOException {

		Socket socket = new Socket("192.168.0.101", 8000); // local host�� �ĵ� �ȴ�.
		System.out.println("�������� �Ϸ�");

		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);

		Scanner sc = new Scanner(System.in);
		System.out.println("������ ������ �޽����� �Է����ּ���.");
		while (true) {
			System.out.print("> ");
			String msg = sc.nextLine();
			dos.writeUTF(msg);
			dos.flush();

			if (msg.equalsIgnoreCase("exit")) {
				break;
			}
		}

		// InputStream in = socket.getInputStream();
		// DataInputStream dis = new DataInputStream(in);

		dos.close();
		sc.close();
		socket.close();
		System.out.println("Ŭ���̾�Ʈ ����");
	}
}
//cmd�� ��������