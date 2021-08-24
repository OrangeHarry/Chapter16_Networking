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
 * AppServer로부터 전달 받은 소켓을 이용하여 클라이언트의 접속 경보 및 송수신 기능을 관리한다.
 */
public class TcpServerHandler implements Runnable {// 스레드를 만들꺼야 고객 하나당 하나씩 생기는

	/*
	 * 클라이언트의 ID를 키(K)로 하는 출력(송신)(V)을 위한 맵구조
	 */
	public static HashMap<String, PrintWriter> sendMap = new HashMap<>();

	// 클라이언트와 연결된 소켓 객체
	private Socket sock;

	// 클라이언트 IP주소
	private String cAddr;

	// 클라이언트 ID
	private String id;

	/*
	 * 생성자 AppServer에서 받아온 소켓을 맵에 저장
	 */
	public TcpServerHandler(Socket clientSocket) {
		this.sock = clientSocket;
		this.cAddr = sock.getInetAddress().getHostAddress();
	}

	/*
	 * 참여자 입,퇴실 관리 브로드캐스팅(참여인원에게 다 뿌려주는것) 참여자 송수신 관리
	 */
	@Override
	public void run() { // 야 읽고 쓰기는 여기서 한다.
		try {
			// 1.송신 스트림 얻기
			PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
			// 2.수신 스트림 얻기
			BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));

			// 3.클라이언트 접속정보 저장
			id = br.readLine();
			TcpServerHandler.sendMap.put(id, printWriter);

			// 4.클라이언트 입장정보를 브로드캐스팅
			TcpServerHandler.broadCast(TcpApplication.TimeStamp() + "[" + id + "]님이 들어 오셨습니다.");
			System.out.println(TcpApplication.TimeStamp() + cAddr + " <- coneccted");
			System.out.println(TcpApplication.TimeStamp() + "참여인원 : " + sendMap.size() + "명");

			// 5. 수신/송신
			String line = null;
			while ((line = br.readLine()) != null) {
				// 퇴장하는 경우
				if (line.equalsIgnoreCase("/quit")) {
					// 퇴장 정보 브로드캐스팅
					TcpServerHandler.broadCast(TcpApplication.TimeStamp() + "[" + id + "]님이 나가셨습니다.");
					break;
				}
				// 귓속말하는 경우
				else if (line.indexOf("/to") > -1) { // 문자열 찾는거잖아 이거
					// 귓속말 메소드 호출
					whisper(id, line);
				}
				// 일반 메시지 전송
				else {
					String message = "[" + id + "]" + " " + line;
					TcpServerHandler.broadCast(message);
				}
			}
			// 퇴장하는 경우 처리
			System.out.println(TcpApplication.TimeStamp() + cAddr + " -> disconnected");

			// 맵 삭제
			TcpServerHandler.sendMap.remove(id);

			System.out.println(TcpApplication.TimeStamp() + "참여인원 : " + sendMap.size() + "명");
			
			printWriter.close();
			br.close();
			sock.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

	/*
	 * 귓속말 메소드
	 * name = 보내는 클라이언트 id
	 * message = 보낼 메세지( ex)/to id 뭐 하니??) 
	 */
	private void whisper(String name, String massage) {
		int start = massage.indexOf(" ") + 1; // 시작위치는 첫 공백의 +1이라는 의미
		int end = massage.indexOf(" ", start); // start위치부터 다음 공백이 나오는 위치 String API찾아봐
		
		if(end != -1) {
			// id = 보낼 클라이언트 id
			String id = massage.substring(start, end);// 첫점과 끝점을 알려줌
			String secret = massage.substring(end + 1);//end부터 문자열 끝까지 추출한다. (end+1)을 한 문자열로 보기때문에
			
			//sendMap으로부터 해당 키<id>에 해당하는 PrintWriter객체를 얻어온다.
			PrintWriter pw = TcpServerHandler.sendMap.get(id);
			//보낼 메세지 전송
			if(pw != null) {
				pw.println(name + "님의 귓속말 : "+ secret);
				pw.flush();
			}
		}
	}

	/*
	 * 메시지 일괄 전송메소드 : 모든 참여자에게 일괄적으로 전송
	 */
	public static void broadCast(String message) {
		// sendMap에 여러 스레드가 접근하므로 동기화(synchronized) 처리 필요
		synchronized (sendMap) { // 메소드 앞에 쓸 수도 있지만 내부에 이렇게도 사용 가능

			// 접속한 모든 클라이언트들에게 메시지 전송
			for (PrintWriter cpw : TcpServerHandler.sendMap.values()) {
				cpw.println(message);
				cpw.flush();
			}
		}
	}
}
