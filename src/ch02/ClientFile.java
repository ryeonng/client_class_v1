package ch02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientFile {

	public static void main(String[] args) {

		// 클라이언트 측 준비물
		// 1. 서버 측  IP 주소와 포트 번호
		// 2. 서버 측 소켓과 연결 될 소켓
		
		Socket socket = null;
		
		try {
			socket = new Socket("localhost", 5001);
			
			PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
			writer.println("안녕 반가워 "); // autoflush 하더라도 줄 바꿈 처리 (print > println) 하자
//			writer.flush(); 
//			PrintWriter writer = new PrintWriter(socket.getOutputStream(),true); > true 를 쓰면 auto flush
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}

}
