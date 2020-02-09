package week05.slot02.chatapplication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(9001);
			System.out.println("Server is running.");
			
			int count = 1;
			while(true) {
				Socket client1 = serverSocket.accept();
				System.out.println("Client connected #" + count);
				int client1Number = count;
				count++;
				Socket client2 = serverSocket.accept();
				System.out.println("Client connected #" + count);
				int client2Number = count;
				count++;
				
				new Thread(
						new ChatSessionHandler(
								client1, client2,
								client1Number, client2Number))
								.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
