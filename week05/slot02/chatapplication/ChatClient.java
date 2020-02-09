package week05.slot02.chatapplication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 9001);
			System.out.println("Connected to server.");
			
			DataOutputStream toServer =
					new DataOutputStream(socket.getOutputStream());
			DataInputStream fromServer =
					new DataInputStream(socket.getInputStream());
			
			Scanner scanner = new Scanner(System.in);
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						try {
							if(fromServer.available() > 0) {
								String receivedMsg = fromServer.readUTF();
								System.out.println(">>> " + receivedMsg);
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}).start();
			
			while(true) {
				System.out.println("Please type a message: ");
				String msgToSend = scanner.nextLine();
				toServer.writeUTF(msgToSend);
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
