package socket_programming;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer2 {
	public static void main(String[] args) throws IOException {
		String str = "안녕하세요 반갑습니다 :)";
		
		ServerSocket server = new ServerSocket(7761);
		System.out.println("서버 준비 완료");
		
		Socket socket = server.accept();
		System.out.println("클라이언트 연결완료");
		//클라이언트 IP주소
		System.out.println(socket.getInetAddress());
		
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out); //빨대를 두개를 꽂았네

	
		dos.writeUTF(str);
		dos.flush();
		dos.close();
		
		socket.close();
		server.close();
		
		System.out.println("서버종료");
	}
}
