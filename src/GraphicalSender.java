import java.io.IOException;
import java.net.*;

public class GraphicalSender {
	private InetAddress address;
	private int port;
	private DatagramSocket socket;
	
	public GraphicalSender(InetAddress destinationAddress,int destinationPort,DatagramSocket socket) {
		address=destinationAddress;
		port=destinationPort;
		this.socket=socket;
	}
	
	public boolean send(String s) {
		try {
			byte[] buf=s.getBytes();
			DatagramPacket packet=new DatagramPacket(buf,buf.length,address,port);
			socket.send(packet);
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}
