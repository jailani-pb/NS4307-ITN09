package week05.slot01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class CircleClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = 
				new Socket("192.168.1.108", 9001);
		System.out.println("Connected to server.");
		
		DataInputStream fromServer =
				new DataInputStream(
						socket.getInputStream());
		DataOutputStream toServer =
				new DataOutputStream(
						socket.getOutputStream());
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("Type in radius:");
			String radiusInput = scanner.nextLine();
			try {
				double radius = 
					Double.parseDouble(radiusInput);
				toServer.writeDouble(radius);
				if(radius <= 0) {
					System.out.println(
							"Disconnected");
					break;
				}
				double area = 
						fromServer.readDouble();
				System.out.println("Radius Sent: "
					+ radius + ", Area Received: "
					+ area);
			} catch (NumberFormatException e) {
				System.out.println("Input invalid.");
			}
		}
	}
	
}
