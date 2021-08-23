package chatting_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import com.framework.TcpApplication;

public class AppClient extends TcpApplication {
	public static PrintWriter pw = null;
	public static BufferedReader keyboard = null;
	public static BufferedReader br = null;
	public static Socket sSock = null;

	// ���� IP�ּ�
	private static String sAddr = null;
	// Ŭ���̾�Ʈ ID
	private String id = null;

	/*
	 * ���ø����̼� �ʱ�ȭ
	 */
	@Override
	public void init() {
		super.init();
	}

	/*
	 * ���ø����̼� ����
	 */
	@Override
	public void start() {

		System.out.println(TimeStamp());
		System.out.println("TCP/IP Ŭ���̾�Ʈ ���α׷��� �����մϴ�.");
		System.out.println("CLIENT START >>>");

		try {
			// 1.���� ����
			System.out.println(TcpApplication.TimeStamp() + " ������ ������...");
			sSock = new Socket(TcpApplication.IP, TcpApplication.PORT); // ������ ������ Ŭ���̾�Ʈ ����
			sAddr = sSock.getInetAddress().getHostAddress();
			System.out.println(TcpApplication.TimeStamp() + sAddr + " <- connected");

			// 2.1�۽� ��Ʈ�� ���
			pw = new PrintWriter(new OutputStreamWriter(sSock.getOutputStream()));
			// 2.2���� ��Ʈ�� ���
			br = new BufferedReader(new InputStreamReader(sSock.getInputStream()));
			// 2.3 Ű���� ���� ��Ʈ�� ���
			keyboard = new BufferedReader(new InputStreamReader(System.in));
			
			// 3.Ŭ���̾�Ʈ�� id�� �����Ѵ�.
			System.out.print("���̵� �Է� > ");
			id = keyboard.readLine();
			pw.println(id); //�갡 ������ ���������� ���ݾ�??����
			pw.flush();
			
			// 4.������ ������ ������ ������ ��� �� �����带 �����Ͽ� ����
			Thread thread = new Thread(new TcpClientHandler());
			thread.start();
			
			// 5.������ �������� Ű���� �Է� ����
			String line= null;
			while ((line = keyboard.readLine()) != null) {
				pw.println(line);
				pw.flush();
				if(line.equalsIgnoreCase("/quit")) {
					break;
				}
			}
			//close()ó�� �����൵ �ǳ�?? ���� �����忡�� closeó�� �����.
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
