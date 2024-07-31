package ch07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MultiClient {

	public static void main(String[] args) {

		try {
			Socket socket = new Socket("192.168.0.48", 5000);
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println(" 서버에 접속 완료 "); 

			// 실행의 흐름 약속 ! : 먼저 사용자 닉네임 보내기
			System.out.println("Enter your name :");
			String name = keyboard.readLine();
			out.println("NAME : " + name);
			
			// 서버 측으로부터 온 데이터 읽기
			Thread readThread = new Thread(() -> {
				String serverMsg;
				try {
					while( (serverMsg = in.readLine()) != null ) {
						System.out.println("server : " + serverMsg);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
			// 클라이언트가 서버로 데이터 보내기
			Thread writeThread = new Thread(() -> {
				String userMessage;
				try {
					while( (userMessage = keyboard.readLine()) != null ) {
						if(userMessage.equalsIgnoreCase("bye")) {
							out.println("BYE:");
						} else {
							out.println("MSG:" + userMessage);
						}
//						} else if(userMessage.equalsIgnoreCase("MSG")) {
//							out.println("MSG:" + userMessage);
//						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
			// 스레드 시작
			readThread.start();
			writeThread.start();
			
			// 메인 스레드 대기
			try {
				readThread.join();
			writeThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			
			socket.close();
			System.out.println("서버로부터 연결을 종료 하였습니다.");
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} // end of main

} // end of class
