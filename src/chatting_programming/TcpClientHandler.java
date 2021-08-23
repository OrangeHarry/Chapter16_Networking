package chatting_programming;

import java.io.IOException;

/**
 * @author hhm12
 * 
 *         AppClient�� ������ �̿��Ͽ� ������ ��/���� ����� �����Ѵ�.
 */
public class TcpClientHandler implements Runnable {

	@Override
	public void run() {
		try {
			//�����κ��� �޽��� ����
			String line = null;
			while(true) {
				line = AppClient.br.readLine(); //�о��
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