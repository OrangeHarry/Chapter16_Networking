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
 * [파일서비스 프로그래밍] 1/클라이언트는 특정한 파일명을 서버에 요청한다. 2.서버는 클라이언트가 요청한 파일을 찾는다. 3.요청한 파일을
 * 찾으면 파일(리소스폴더 내)에서 데이터를 읽어 들인다. 4.읽어 온 파일의 데이터를 클라이언트에게 전송한다(던져) 5.클라이언트는 받은
 * 파일의 내용을 화면에 출력하고 저장한다.
 */

//FTP서버는 파일을 갖고있는, 파일이 저장되어 있는 서버(필요할때 다운받을 수 있는)
public class FTPServer {
	private static final int PORT = 7761;

	public static void main(String[] args) { // 메인메소드는 static이네
		// (입출력 스트림)
		InputStream in = null; // 참조클래스니깐 null로 초기화
		OutputStream out = null;
		DataInputStream din = null;
		DataOutputStream dout = null;
		FileInputStream fin = null;

		// (소켓)
		ServerSocket serverSoc = null;
		Socket clientSoc = null;

		// 호스트 주소(ip주소)
		String clientAddr = null;

		/*
		 * 클라이언트 접속 대기 및 소켓생성 수신/송신
		 */
		try {
			serverSoc = new ServerSocket(PORT);
			System.out.println(TimeStamp() + "클라이언트 접속을 대기중입니다...");
			System.out.println();

			clientSoc = serverSoc.accept();
			clientAddr = clientSoc.getInetAddress().getHostName();// 접속 주소를 스트링으로 반환해준다.
			System.out.println(TimeStamp() + clientAddr + "<-connected.");

			/*
			 * 수신(IN) : 클라이언트가 보낸 파일명
			 */
			in = clientSoc.getInputStream();
			din = new DataInputStream(in);

			/*
			 * 송신(OUT) : 서버소스폴더 내 파일을 클라이언트에게 보낸다.
			 */
			out = clientSoc.getOutputStream();
			dout = new DataOutputStream(out);

			// 1단계 : 사용자가 무슨 파일을 필요로 하는지 파악
			String fileName = din.readUTF();

			// 2단계 : 파일데이터를 찾아내고 데이터를 읽는다.
			fin = new FileInputStream("resources\\" + fileName); // 절대경로를 입력하지 않는다. 상대경로 사용

			// 3단계 : 파일데이터를 읽고 데이터를 보낸다.
			System.out.println("파일을 보내는중...");
			System.out.println();
			while (true) {
				// 읽기
				int data = fin.read();
				if (data == -1)
					break;
				// 쓰기
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
			System.out.println(TimeStamp() + "파일 전송 서비스를 종료합니다.");
		}
	}

	/*
	 * 현재시간을 리턴해주는 메소드 반환타입 : String
	 */
	private static String TimeStamp() {
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
		return format.format(new Date());// 현재 시간을 리턴할 수 있다.
	}
}
