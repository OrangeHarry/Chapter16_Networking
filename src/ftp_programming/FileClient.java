package ftp_programming;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class FileClient {
	public static final String IP = "localhost";
	public static final int PORT = 7254;

	public static void main(String[] args) {
		try {
			Socket socket = new Socket(IP, PORT);
			
			InputStream in = socket.getInputStream();
			InputStreamReader inr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(inr);
			
			DataInputStream din = new DataInputStream(in);
			FileOutputStream fout = null;  //파일을 가져와서 다운을 받아야징
			
			while(true) {
				//파일 이름 수신
				String strline = br.readLine();
				String strlen = br.readLine();
				
				if(strline == null) break;
				System.out.println(strline + "파일 수신중...");
				System.out.println("파일크기 : ["+strlen+"]");
				
				String path = "C:/Temp/Download/"+strline;
				fout = new FileOutputStream(path);
				
				int cnt=0;
				for(int i=0; i<Integer.parseInt(strlen); i++) {
					//파일 수신
					int data = din.read();
					//파일 쓰기
					fout.write(data);
					
					if(cnt % 3000 == 0) {System.out.print("★");}
					cnt++;
				}
				System.out.println("완료\n");
			}
			fout.close();
			din.close();
			br.close();
			socket.close();
			System.out.println();
			System.out.println("파일 요청프로그램을 종료 합니다.");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
