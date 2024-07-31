 package ch05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// 1단계 - 함수로 분리해서 리팩토링 진행
public class MultiThreadClient {

	public static void main(String[] args) {

		try (Socket socket = new Socket("localhost", 5000)){
						
			PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(),true);
			
			BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
			
			readStartThread(socketReader);
			writeStartThread(socketWriter, keyboardReader);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	} // end of main

	// 서버로부터 받는 스레드
	private static void readStartThread(BufferedReader socketReader) {
		Thread readThread = new Thread(() -> {
			String serverMessage;
			try {
				while( (serverMessage = socketReader.readLine()) != null ) {
					System.out.println("server chat : " + serverMessage);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		readThread.start();
	}
	
	
	// 서버에게 보내는 스레드
	private static void writeStartThread(PrintWriter socketWriter, BufferedReader keyboardReader) {
		Thread writeThread = new Thread(() -> {
			try {
			String clientMessage;
			while( (clientMessage = keyboardReader.readLine()) != null ) {
				socketWriter.println(clientMessage);
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		writeThread.start();
	}
	
	// 워커 스레드가 종료될 때 까지 기다리는 메서드
	private static void waitForThreadToEnd(Thread thread) {
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
} // end of class
