package chatting_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import com.framework.TcpApplication;

/**
 * AppServer�κ��� ���� ���� ������ �̿��Ͽ� Ŭ���̾�Ʈ�� ���� �溸 �� �ۼ��� ����� �����Ѵ�.
 */
public class TcpServerHandler implements Runnable {// �����带 ���鲨�� �� �ϳ��� �ϳ��� �����

	/*
	 * Ŭ���̾�Ʈ�� ID�� Ű(K)�� �ϴ� ���(�۽�)(V)�� ���� �ʱ���
	 */
	public static HashMap<String, PrintWriter> sendMap = new HashMap<>();

	// Ŭ���̾�Ʈ�� ����� ���� ��ü
	private Socket sock;

	// Ŭ���̾�Ʈ IP�ּ�
	private String cAddr;

	// Ŭ���̾�Ʈ ID
	private String id;

	/*
	 * ������ AppServer���� �޾ƿ� ������ �ʿ� ����
	 */
	public TcpServerHandler(Socket clientSocket) {
		this.sock = clientSocket;
		this.cAddr = sock.getInetAddress().getHostAddress();
	}

	/*
	 * ������ ��,��� ���� ��ε�ĳ����(�����ο����� �� �ѷ��ִ°�) ������ �ۼ��� ����
	 */
	@Override
	public void run() { // �� �а� ����� ���⼭ �Ѵ�.
		try {
			// 1.�۽� ��Ʈ�� ���
			PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
			// 2.���� ��Ʈ�� ���
			BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));

			// 3.Ŭ���̾�Ʈ �������� ����
			id = br.readLine();
			TcpServerHandler.sendMap.put(id, printWriter);

			// 4.Ŭ���̾�Ʈ ���������� ��ε�ĳ����
			TcpServerHandler.broadCast(TcpApplication.TimeStamp() + "[" + id + "]���� ��� ���̽��ϴ�.");
			System.out.println(TcpApplication.TimeStamp() + cAddr + " <- coneccted");
			System.out.println(TcpApplication.TimeStamp() + "�����ο� : " + sendMap.size() + "��");

			// 5. ����/�۽�
			String line = null;
			while ((line = br.readLine()) != null) {
				// �����ϴ� ���
				if (line.equalsIgnoreCase("/quit")) {
					// ���� ���� ��ε�ĳ����
					TcpServerHandler.broadCast(TcpApplication.TimeStamp() + "[" + id + "]���� �����̽��ϴ�.");
					break;
				}
				// �ӼӸ��ϴ� ���
				else if (line.indexOf("/to") > -1) { // ���ڿ� ã�°��ݾ� �̰�
					// �ӼӸ� �޼ҵ� ȣ��
					whisper(id, line);
				}
				// �Ϲ� �޽��� ����
				else {
					String message = "[" + id + "]" + " " + line;
					TcpServerHandler.broadCast(message);
				}
			}
			// �����ϴ� ��� ó��
			System.out.println(TcpApplication.TimeStamp() + cAddr + " -> disconnected");

			// �� ����
			TcpServerHandler.sendMap.remove(id);

			System.out.println(TcpApplication.TimeStamp() + "�����ο� : " + sendMap.size() + "��");
			
			printWriter.close();
			br.close();
			sock.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

	/*
	 * �ӼӸ� �޼ҵ�
	 * name = ������ Ŭ���̾�Ʈ id
	 * message = ���� �޼���( ex)/to id �� �ϴ�??) 
	 */
	private void whisper(String name, String massage) {
		int start = massage.indexOf(" ") + 1; // ������ġ�� ù ������ +1�̶�� �ǹ�
		int end = massage.indexOf(" ", start); // start��ġ���� ���� ������ ������ ��ġ String APIã�ƺ�
		
		if(end != -1) {
			// id = ���� Ŭ���̾�Ʈ id
			String id = massage.substring(start, end);// ù���� ������ �˷���
			String secret = massage.substring(end + 1);//end���� ���ڿ� ������ �����Ѵ�. (end+1)�� �� ���ڿ��� ���⶧����
			
			//sendMap���κ��� �ش� Ű<id>�� �ش��ϴ� PrintWriter��ü�� ���´�.
			PrintWriter pw = TcpServerHandler.sendMap.get(id);
			//���� �޼��� ����
			if(pw != null) {
				pw.println(name + "���� �ӼӸ� : "+ secret);
				pw.flush();
			}
		}
	}

	/*
	 * �޽��� �ϰ� ���۸޼ҵ� : ��� �����ڿ��� �ϰ������� ����
	 */
	public static void broadCast(String message) {
		// sendMap�� ���� �����尡 �����ϹǷ� ����ȭ(synchronized) ó�� �ʿ�
		synchronized (sendMap) { // �޼ҵ� �տ� �� ���� ������ ���ο� �̷��Ե� ��� ����

			// ������ ��� Ŭ���̾�Ʈ�鿡�� �޽��� ����
			for (PrintWriter cpw : TcpServerHandler.sendMap.values()) {
				cpw.println(message);
				cpw.flush();
			}
		}
	}
}
