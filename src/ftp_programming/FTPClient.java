package ftp_programming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FTPClient {

	public static final String IP = "localhost";
	// public static final String IP = "192.168.0.101"; ������ ���� �ΰ���

	public static final int PORT = 7761;

	public static void main(String[] args) {
		// (����� ��Ʈ��)
		InputStream in = null; // ����Ŭ�����ϱ� null�� �ʱ�ȭ
		OutputStream out = null;
		DataInputStream din = null;
		DataOutputStream dout = null;
		FileOutputStream fos = null;
		Scanner sc = null;

		// ����
		Socket socket = null;

		try {
			socket = new Socket(IP, PORT);
			sc = new Scanner(System.in);

			// ����
			in = socket.getInputStream(); // Ŭ���̾�Ʈ ����(?)
			din = new DataInputStream(in);

			// �۽�
			out = socket.getOutputStream();
			dout = new DataOutputStream(out);

			menuDisplay();
			String filename = sc.nextLine();

			// ������ ����
			dout.writeUTF(filename);
			System.out.println(TimeStamp() + "���� ������ ��û�Ͽ����ϴ�.");
			System.out.println();

			// ���ϵ����� ����
			System.out.println("���� ������...");
			System.out.println();

			// ������ ������ ��
			fos = new FileOutputStream("C:/Temp/" + filename);
			
			int cnt = 0;
			while (true) { // ������ �ް� ������ �ؾ���
				// �б�
				int data = din.read();
				if (data == -1)
					break;
				// ����
				fos.write(data);
				if(cnt % 5000 == 0) {
				System.out.print("��");
				}
				cnt++;
			}
			System.out.println();
			System.out.println(TimeStamp() + "���� ������ �Ϸ��Ͽ����ϴ�."+ "("+cnt+"����Ʈ)");
			
			System.out.println();
			System.out.println(TimeStamp() + "���� �ٿ�ε� �Ϸ�");
			System.out.println();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (din != null) {din.close();}
				if (dout != null) {dout.close();}
				if (fos != null) {fos.close();}
				if (socket != null) {socket.close();}

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println(TimeStamp() + "Ŭ���̾�Ʈ ���� ��û ���α׷��� �����մϴ�.");
	}

	private static void menuDisplay() {
		System.out.println();
		System.out.println("����������������������������������������������������������������������������������������������������������������������������������������");
		System.out.println();
		System.out.println("              �ް� ���� ������ �̸�(���ϸ�.Ȯ����)�� �Է��� �ּ���  ");
		System.out.println();
		System.out.println("    [1]aaa.txt [2]bbb.txt [3]ccc.txt [4]Harry.txt [5]image.jpg");
		System.out.println();
		System.out.println("����������������������������������������������������������������������������������������������������������������������������������������");
		System.out.print("���� > ");
	}

	/*
	 * ����ð��� �������ִ� �޼ҵ� ��ȯŸ�� : String
	 */
	private static String TimeStamp() {
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
		return format.format(new Date());// ���� �ð��� ������ �� �ִ�.
	}
}
