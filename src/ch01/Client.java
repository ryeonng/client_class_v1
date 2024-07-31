package ch01;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		
		// * 클라이언트 측에서 소켓 통신을 하기위한 준비물
		// 1. 서버 측 컴퓨터의 주소 : 포트 번호 필요
		// 2. 서버측과 연결 될 기본 소켓이 필요
		
		// 생성자 매개변수에 서버 측 IP주소를 쓰고, 포트 번호를 넣어준다.
		// 127.0.0.1 <- 자신의 IP주소 (localhost)
		try (Socket socket = new Socket("192.168.0.116",5000)) {
			// new Socket("localhost",5000) : 객체 생성 시 서버측과 연결 되어 스트림을 활용할 수 있다.'
			// 대상은 소켓이다. 
			OutputStream output = socket.getOutputStream(); // 소켓에서 기반 스트림을 꺼냄
			PrintWriter writer = new PrintWriter(output,true); // 기능의 확장을 위한 보조 스트림
			writer.println("안녕"); // dkddddd
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
