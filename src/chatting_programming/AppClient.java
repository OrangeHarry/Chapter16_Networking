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

	// 서버 IP주소
	private static String sAddr = null;
	// 클라이언트 ID
	private String id = null;

	/*
	 * 어플리케이션 초기화
	 */
	@Override
	public void init() {
		super.init();
	}

	/*
	 * 어플리케이션 실행
	 */
	@Override
	public void start() {

		System.out.println(TimeStamp());
		System.out.println("TCP/IP 클라이언트 프로그램을 시작합니다.");
		System.out.println("CLIENT START >>>");

		try {
			// 1.서버 연결
			System.out.println(TcpApplication.TimeStamp() + " 서버에 연결중...");
			sSock = new Socket(TcpApplication.IP, TcpApplication.PORT); // 서버에 접속한 클라이언트 소켓
			sAddr = sSock.getInetAddress().getHostAddress();
			System.out.println(TcpApplication.TimeStamp() + sAddr + " <- connected");

			// 2.1송신 스트림 얻기
			pw = new PrintWriter(new OutputStreamWriter(sSock.getOutputStream()));
			// 2.2수신 스트림 얻기
			br = new BufferedReader(new InputStreamReader(sSock.getInputStream()));
			// 2.3 키보드 수신 스트림 얻기
			keyboard = new BufferedReader(new InputStreamReader(System.in));
			
			// 3.클라이언트의 id를 전송한다.
			System.out.print("아이디 입력 > ");
			id = keyboard.readLine();
			pw.println(id); //얘가 서버로 보내버리는 거잖아??ㅎㅎ
			pw.flush();
			
			// 4.접속한 서버의 데이터 수신을 담당 할 스레드를 생성하여 실행
			Thread thread = new Thread(new TcpClientHandler());
			thread.start();
			
			// 5.접속한 서버에게 키보드 입력 전송
			String line= null;
			while ((line = keyboard.readLine()) != null) {
				pw.println(line);
				pw.flush();
				if(line.equalsIgnoreCase("/quit")) {
					break;
				}
			}
			//close()처리 안해줘도 되나?? 구현 스레드에서 close처리 해줬다.
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
