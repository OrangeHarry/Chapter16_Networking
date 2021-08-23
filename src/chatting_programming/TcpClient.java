package chatting_programming;

import java.util.Scanner;

import com.framework.TcpApplication;

public class TcpClient {
	public static void main(String[] args) {
		//그냥 ui 깔끔하게 해주는용도
		for(int i=0; i<20; i++) {
			System.out.println("");
		}
		
		Scanner sc = new Scanner(System.in);

		showMenu();
		System.out.print("            >");
		int select = sc.nextInt();
		
		switch (select) {
		case 1:
			System.out.printf("          1 → Start                   \n");
			System.out.println();
			break;
		case 0 :
			System.exit(0);
		}
		
		/*
		 * TCP/IP 어플리케이션 생성
		 */
		TcpApplication app = new AppClient();
		
		//설정 초기화
		app.init();
		
		//어플리케이션 실행
		app.start();
		
		sc.close();
	}
	
	 public static void showMenu() {
	        System.out.printf("                                      \n");
	        System.out.printf("          ■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n");
	        System.out.printf("                                      \n");
	        System.out.printf("            [CLIENT version 1.0.1®]   \n");
	        System.out.printf("                                      \n");
	        System.out.printf("          ■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n");
	        System.out.printf("                                      \n");
	        System.out.printf("                                      \n");
	        System.out.printf("                                      \n");
	        
	        System.out.printf("          1 → Start                   \n");
	        System.out.printf("          0 → End                     \n");
	        System.out.printf("                                      \n");
	        System.out.printf("                                      \n");
	    }
}
