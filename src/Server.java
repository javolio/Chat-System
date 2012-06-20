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

/*First the server will start up and pick a port number,and then it
 * prints out the IP address and the port number onto the screen.
 * After that, the server sits there waiting for client’s
 * communication.
 * Now, you and your partner can start the IM-mimicking communication.
 * Basically, you type in one sentence, and the sentence is sent over
 * through the network and displayed on your partner’s machine (the
 * server). You partner can type in a sentence and it will be
 * displayed on your screen. You and your partner can reply to each
 * other’s message.
 */
public class Server {
	public static void main(String[] args) throws IOException {
		DatagramSocket socket=new DatagramSocket();
		
		//Display the local address and port
		System.out.println("IP Address: "+InetAddress.getLocalHost().getHostAddress());
		System.out.println("Port: "+socket.getLocalPort());
		
		//Receive the initial message
		byte[] buf=new byte[1];
		DatagramPacket packet=new DatagramPacket(buf,buf.length);
		socket.receive(packet);
		InetAddress destinationAddress=packet.getAddress();
		int destinationPort=packet.getPort();
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
			m.receive();
			m.send();
		}
		/**/
	}
}
