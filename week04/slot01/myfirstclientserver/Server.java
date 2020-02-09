package week04.slot01.myfirstclientserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
// 10.106.133.106
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket =
				new ServerSocket(9001);
		System.out.println("Server is running");
		Socket socket = serverSocket.accept();
		System.out.println("A client has connected");
		
		DataInputStream fromClient =
				new DataInputStream(
						socket.getInputStream());
		
		while(true) {
			String receivedData = fromClient.readUTF();
			System.out.println(
					"Received: " + receivedData);
			if(receivedData.equalsIgnoreCase("q")) {
				System.out.println("Closing server");
				break;
			}
		}
		
//		int count = 0;
//		Socket socket = null;
//		while(true) {
//			socket = serverSocket.accept();
//			System.out.println("A client has connected");
//			count++;
//			if(count >= 1000) {
//				break;
//			}
//		}
		
		serverSocket.close();
		socket.close();
	}
	
}





