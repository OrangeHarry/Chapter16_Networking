package chatting_programming;

import java.util.Scanner;

import com.framework.TcpApplication;

public class TcpServer {
	public static void main(String[] args) {
		//�׳� ui ����ϰ� ���ִ¿뵵
		for(int i=0; i<20; i++) {
			System.out.println("");
		}
		
		Scanner sc = new Scanner(System.in);
		
		showMenu();
		System.out.print("            >");
		int select = sc.nextInt();
		
		switch (select) {
		case 1:
			System.out.printf("          1 �� Start                   \n");
			System.out.println();
			break;
		case 0 :
			System.exit(0);
		}
		
		/*
		 * TCP/IP ���ø����̼� ����
		 */
		TcpApplication app = new AppServer();
		
		//���� �ʱ�ȭ
		app.init();
		
		//���ø����̼� ����
		app.start();
		
		sc.close();
	}
	
	 public static void showMenu() {
	        System.out.printf("                                      \n");
	        System.out.printf("          �����������������������������\n");
	        System.out.printf("                                      \n");
	        System.out.printf("            [SERVER version 1.0.1��]   \n");
	        System.out.printf("                                      \n");
	        System.out.printf("          �����������������������������\n");
	        System.out.printf("                                      \n");
	        System.out.printf("                                      \n");
	        System.out.printf("                                      \n");
	        
	        System.out.printf("          1 �� Start                   \n");
	        System.out.printf("          0 �� End                     \n");
	        System.out.printf("                                      \n");
	        System.out.printf("                                      \n");
	    }
}