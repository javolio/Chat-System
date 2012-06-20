//----------------------------------------------------------------
//Joseph Avolio
//04/22/2012
//COSC 335
//Project 1
//----------------------------------------------------------------
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

/*The client starts up and asks for server’s socket information. You
 * type in the socket information based on the server’s screen
 * print-out. The client binds to the socket and sits there waiting
 * for user keyboard input.
 * Now, you and your partner can start the IM-mimicking communication.
 * Basically, you type in one sentence, and the sentence is sent over
 * through the network and displayed on your partner’s machine (the
 * server). You partner can type in a sentence and it will be
 * displayed on your screen. You and your partner can reply to each
 * other’s message.
 */
public class Client {
	public static void main(String[] args) throws IOException {
		Scanner scan=new Scanner(System.in);
		DatagramSocket socket=new DatagramSocket();
		byte[] buf=new byte[1];
		boolean valid=false;
		InetAddress destinationAddress=null;
		
		//Read in a valid IP Address
		while (!valid) {
			System.out.print("Enter Server IP Address: ");
			try {
				destinationAddress=InetAddress.getByName(scan.nextLine());
				valid=true;
			} catch (UnknownHostException e) {
				System.out.println("Error: Invalid IP Address");
			}
		}
		valid=false;
		int destinationPort=0;
		DatagramPacket packet=null;
		
		//Read in a valid port
		while (!valid) {
			System.out.print("Enter Server Port: ");
			destinationPort=scan.nextInt();
			try {
				packet=new DatagramPacket(buf,buf.length,destinationAddress,destinationPort);
				valid=true;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		socket.send(packet);
		/**/
		new MessengerFrame(new GraphicalSender(destinationAddress,destinationPort,socket),new GraphicalReceiver(socket));
		/**
		new Sender(destinationAddress,destinationPort,socket);
		new Receiver(socket);
		/**
		
		//Set up a TwoWayMessenger with the socket and destination
		TwoWayMessenger m=new TwoWayMessenger(destinationAddress,destinationPort,socket);
		
		//Continually send and receive messages until done
		while (true) {
			m.send();
			m.receive();
		}
		/**/
	}
}
