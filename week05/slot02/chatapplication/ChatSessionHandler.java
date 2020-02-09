package week05.slot02.chatapplication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ChatSessionHandler implements Runnable {

	Socket client1, client2;
	int client1Number, client2Number;
	
	public ChatSessionHandler(Socket client1, Socket client2,
			int client1Number, int client2Number) {
		this.client1 = client1;
		this.client2 = client2;
		this.client1Number = client1Number;
		this.client2Number = client2Number;
	}
	
	@Override
	public void run() {
		try {
			DataOutputStream toClient1 =
					new DataOutputStream(client1.getOutputStream());
			DataInputStream fromClient1 =
					new DataInputStream(client1.getInputStream());
			
			DataOutputStream toClient2 =
					new DataOutputStream(client2.getOutputStream());
			DataInputStream fromClient2 =
					new DataInputStream(client2.getInputStream());
			
			while(true) {
				if(fromClient1.available() > 0) {
					String client1Msg = fromClient1.readUTF();
					toClient2.writeUTF(client1Msg);
					System.out.println("From Client " + client1Number
							+ " to Client " + client2Number + ": " 
							+ client1Msg);
				}
				if(fromClient2.available() > 0) {
					String client2Msg = fromClient2.readUTF();
					toClient1.writeUTF(client2Msg);
					System.out.println("From Client " + client2Number
							+ " to Client " + client1Number + ": " 
							+ client2Msg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
