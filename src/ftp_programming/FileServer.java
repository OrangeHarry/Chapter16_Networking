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
			System.out.println("클라이언트 접속을 대기합니다...");
			
			Socket clientSo = server.accept(); //결국은 클라이언트 정보가 담겨있는 소켓이징
			
			System.out.println(clientSo.getInetAddress().getHostName()+"님이 접속하였습니다.");
			
			OutputStream out = clientSo.getOutputStream();
			OutputStreamWriter outw = new OutputStreamWriter(out);
			PrintWriter pw = new PrintWriter(outw); //문자같은 거 쓸 때 많이 사용한다.
			
			FileInputStream fin = null;
			DataOutputStream dout = new DataOutputStream(out);
			
			/*
			 * 리소스 자동배포
			 */
			File rescs = new File("resources\\");
			System.out.println(rescs.list().length+"개의 파일이 있습니다.");//경로 폴더 안에 들어있는 파일의 갯수를 알수 있다.
			System.out.println();
			String filePath = null;
			
			for(String file : rescs.list()) {
				//파일정보(이름/크기)
				filePath = rescs.getName() + "\\" + file;
				File sendFile = new File(filePath);
				System.out.println(sendFile.getName()+"송신중...");
				
				//파일 송신
				pw.println(sendFile);
				pw.flush();
				pw.println(sendFile.length());
				pw.flush();
				
				fin = new FileInputStream(filePath);
				int cnt=0;
				for(int i=0; i<sendFile.length(); i++) {
					//파일 읽기
					int data = fin.read();
					//파일 쓰기
					dout.write(data);
					
					if(cnt % 3000 == 0) {System.out.print("★");}
					cnt++;
				}
				System.out.println("완료\n");
			}
			fin.close();
			dout.close();
			pw.close();
			clientSo.close();
			server.close();
			System.out.println();
			System.out.println("파일 배포 서비스 프로그램 종료");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
