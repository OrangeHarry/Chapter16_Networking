package ftp_programming;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
	public static final int PORT = 7254;
	
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(PORT);
			System.out.println("Ŭ���̾�Ʈ ������ ����մϴ�...");
			
			Socket clientSo = server.accept(); //�ᱹ�� Ŭ���̾�Ʈ ������ ����ִ� ������¡
			
			System.out.println(clientSo.getInetAddress().getHostName()+"���� �����Ͽ����ϴ�.");
			
			OutputStream out = clientSo.getOutputStream();
			OutputStreamWriter outw = new OutputStreamWriter(out);
			PrintWriter pw = new PrintWriter(outw); //���ڰ��� �� �� �� ���� ����Ѵ�.
			
			FileInputStream fin = null;
			DataOutputStream dout = new DataOutputStream(out);
			
			/*
			 * ���ҽ� �ڵ�����
			 */
			File rescs = new File("resources\\");
			System.out.println(rescs.list().length+"���� ������ �ֽ��ϴ�.");//��� ���� �ȿ� ����ִ� ������ ������ �˼� �ִ�.
			System.out.println();
			String filePath = null;
			
			for(String file : rescs.list()) {
				//��������(�̸�/ũ��)
				filePath = rescs.getName() + "\\" + file;
				File sendFile = new File(filePath);
				System.out.println(sendFile.getName()+"�۽���...");
				
				//���� �۽�
				pw.println(sendFile);
				pw.flush();
				pw.println(sendFile.length());
				pw.flush();
				
				fin = new FileInputStream(filePath);
				int cnt=0;
				for(int i=0; i<sendFile.length(); i++) {
					//���� �б�
					int data = fin.read();
					//���� ����
					dout.write(data);
					
					if(cnt % 3000 == 0) {System.out.print("��");}
					cnt++;
				}
				System.out.println("�Ϸ�\n");
			}
			fin.close();
			dout.close();
			pw.close();
			clientSo.close();
			server.close();
			System.out.println();
			System.out.println("���� ���� ���� ���α׷� ����");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
