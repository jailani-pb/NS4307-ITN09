package week05.slot01.multipleclientserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultipleClientCircleServer {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = 
				new ServerSocket(9001);
		System.out.println("Server is running.");
		
		while(true) {
			Socket socket = serverSocket.accept();
			System.out.println(
					"Client has connected");
		
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						DataInputStream fromClient =
								new DataInputStream(
										socket.getInputStream());
						DataOutputStream toClient =
								new DataOutputStream(
										socket.getOutputStream());
						
						while(true) {
							double radius = fromClient.readDouble();
							
							if(radius <= 0) {
								System.out.println(
										"Client disconnected");
								break;
							}
							
							double area = Math.PI * radius * radius;
							toClient.writeDouble(area);
							System.out.println("Radius received: "
									+ radius + ", Area sent: "
									+ area);
						}
					} catch (Exception e) {
						System.out.println(
								"Ada Problem.");
					}
				}
				
			}).start();
		}
		
	}
	
}
