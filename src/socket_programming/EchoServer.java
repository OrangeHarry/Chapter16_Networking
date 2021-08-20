package socket_programming;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static void main(String[] args) throws IOException {
		String str = "�ȳ��ϼ��� �ݰ����ϴ� :)";
		
		ServerSocket server = new ServerSocket(7761);
		System.out.println("���� �غ� �Ϸ�");
		
		Socket socket = server.accept();
		System.out.println("Ŭ���̾�Ʈ ����Ϸ�");
		//Ŭ���̾�Ʈ IP�ּ�
		System.out.println(socket.getInetAddress());
		
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out); //���븦 �ΰ��� �Ⱦҳ�

	
		dos.writeUTF(str);
		dos.flush();
		dos.close();
		
		socket.close();
		server.close();
		
		System.out.println("��������");
	}
}
