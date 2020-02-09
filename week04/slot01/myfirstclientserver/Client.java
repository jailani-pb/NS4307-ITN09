package week04.slot01.myfirstclientserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 9001);
		System.out.println("Connected to the server");
		Scanner scanner = new Scanner(System.in);
		
		DataOutputStream toServer = 
				new DataOutputStream(
						socket.getOutputStream());
		
		while(true) {
			System.out.println("Please type to send data");
			String sendingData = scanner.nextLine();
			toServer.writeUTF(sendingData);
			if(sendingData.equalsIgnoreCase("q")) {
				System.out.println("Stop client");
				break;
			}
		}
		
		socket.close();
		scanner.close();
	}
	
}
