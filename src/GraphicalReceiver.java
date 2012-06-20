import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.LinkedList;
import java.util.Queue;

public class GraphicalReceiver implements Runnable {
	private DatagramSocket socket;
	private Queue<String> messages;
	
	public GraphicalReceiver(DatagramSocket socket) {
		this.socket=socket;
		messages=new LinkedList<String>();
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				byte[] buf=new byte[524288];
				DatagramPacket packet=new DatagramPacket(buf,buf.length);
				socket.receive(packet);
				String message=new String(packet.getData());
				write(message.substring(0,message.indexOf(0)));
			} catch (IOException e) {
			}
		}
	}
	
	public synchronized String read() {
		return messages.poll();
	}
	
	public synchronized boolean hasNext() {
		return !messages.isEmpty();
	}
	
	public synchronized void write(String s) {
		messages.add(s);
	}
}
