package ftp_programming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * [���ϼ��� ���α׷���] 1/Ŭ���̾�Ʈ�� Ư���� ���ϸ��� ������ ��û�Ѵ�. 2.������ Ŭ���̾�Ʈ�� ��û�� ������ ã�´�. 3.��û�� ������
 * ã���� ����(���ҽ����� ��)���� �����͸� �о� ���δ�. 4.�о� �� ������ �����͸� Ŭ���̾�Ʈ���� �����Ѵ�(����) 5.Ŭ���̾�Ʈ�� ����
 * ������ ������ ȭ�鿡 ����ϰ� �����Ѵ�.
 */

//FTP������ ������ �����ִ�, ������ ����Ǿ� �ִ� ����(�ʿ��Ҷ� �ٿ���� �� �ִ�)
public class FTPServer {
	private static final int PORT = 7761;

	public static void main(String[] args) { // ���θ޼ҵ�� static�̳�
		// (����� ��Ʈ��)
		InputStream in = null; // ����Ŭ�����ϱ� null�� �ʱ�ȭ
		OutputStream out = null;
		DataInputStream din = null;
		DataOutputStream dout = null;
		FileInputStream fin = null;

		// (����)
		ServerSocket serverSoc = null;
		Socket clientSoc = null;

		// ȣ��Ʈ �ּ�(ip�ּ�)
		String clientAddr = null;

		/*
		 * Ŭ���̾�Ʈ ���� ��� �� ���ϻ��� ����/�۽�
		 */
		try {
			serverSoc = new ServerSocket(PORT);
			System.out.println(TimeStamp() + "Ŭ���̾�Ʈ ������ ������Դϴ�...");
			System.out.println();

			clientSoc = serverSoc.accept();
			clientAddr = clientSoc.getInetAddress().getHostName();// ���� �ּҸ� ��Ʈ������ ��ȯ���ش�.
			System.out.println(TimeStamp() + clientAddr + "<-connected.");

			/*
			 * ����(IN) : Ŭ���̾�Ʈ�� ���� ���ϸ�
			 */
			in = clientSoc.getInputStream();
			din = new DataInputStream(in);

			/*
			 * �۽�(OUT) : �����ҽ����� �� ������ Ŭ���̾�Ʈ���� ������.
			 */
			out = clientSoc.getOutputStream();
			dout = new DataOutputStream(out);

			// 1�ܰ� : ����ڰ� ���� ������ �ʿ�� �ϴ��� �ľ�
			String fileName = din.readUTF();

			// 2�ܰ� : ���ϵ����͸� ã�Ƴ��� �����͸� �д´�.
			fin = new FileInputStream("resources\\" + fileName); // �����θ� �Է����� �ʴ´�. ����� ���

			// 3�ܰ� : ���ϵ����͸� �а� �����͸� ������.
			System.out.println("������ ��������...");
			System.out.println();
			while (true) {
				// �б�
				int data = fin.read();
				if (data == -1)
					break;
				// ����
				dout.write(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (din != null) {din.close();}
				if (dout != null) {dout.close();}
				if (fin != null) {fin.close();}
				if (clientSoc != null) {clientSoc.close();}
				if (serverSoc != null) {serverSoc.close();}

			} catch (IOException ex) {
				ex.printStackTrace();
			}
			System.out.println(TimeStamp() + "���� ���� ���񽺸� �����մϴ�.");
		}
	}

	/*
	 * ����ð��� �������ִ� �޼ҵ� ��ȯŸ�� : String
	 */
	private static String TimeStamp() {
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
		return format.format(new Date());// ���� �ð��� ������ �� �ִ�.
	}
}
