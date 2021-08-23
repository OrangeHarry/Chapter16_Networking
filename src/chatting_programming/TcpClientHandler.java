package chatting_programming;

import java.io.IOException;

/**
 * @author hhm12
 * 
 *         AppClient의 소켓을 이용하여 서버의 송/수신 기능을 관리한다.
 */
public class TcpClientHandler implements Runnable {

	@Override
	public void run() {
		try {
			//서버로부터 메시지 수신
			String line = null;
			while(true) {
				line = AppClient.br.readLine(); //읽어와
				if(line != null) {
					System.out.println(line);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(AppClient.keyboard != null) {AppClient.keyboard.close();}
				if(AppClient.pw != null) {AppClient.pw.close();}
				if(AppClient.br != null) {AppClient.br.close();}
				if(AppClient.sSock != null) {AppClient.sSock.close();}
					
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
}
