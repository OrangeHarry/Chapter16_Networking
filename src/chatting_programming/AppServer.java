package chatting_programming;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.framework.TcpApplication;

public class AppServer extends TcpApplication {
	
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
		System.out.println("TCP/IP �������α׷��� �����մϴ�.");
		System.out.println("SERVER START >>>");
		
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		Thread th = null;
		
		try {
			//1.���� ���� ����
			serverSocket = new ServerSocket(PORT); //TcpApplication.PORT��� ���൵ �ȴ�.

			//2.Ŭ���̾�Ʈ�� ����� ���� ����
			while(true) {
				
				clientSocket = serverSocket.accept();
				
				System.out.println(TimeStamp());
				System.out.println("Ŭ���̾�Ʈ ���� �����...");
				
				/* ������ Ŭ���̾�Ʈ �ۼ����� ����� �����带 �����Ͽ� ����(clientSocket)�� �����Ѵ�.
				 * ������ ����ŭ �����尡 �����ȴ�.
				 */
				th = new Thread(new TcpServerHandler(clientSocket));
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
