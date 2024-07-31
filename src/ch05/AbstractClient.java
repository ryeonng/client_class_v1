package ch05;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// 2단계 - 상속 활용 리팩토링 단계
public abstract class AbstractClient {
	
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader rederStream;
	private PrintWriter writerStream;
	private BufferedReader keyboardReader;
	
	// 
	
}
