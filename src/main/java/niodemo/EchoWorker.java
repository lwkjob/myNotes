package niodemo;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

public class EchoWorker implements Runnable {
	private List linkedList = new LinkedList();
	
	public void processData(NioServer server, SocketChannel socket, byte[] data, int count) {
		byte[] dataCopy = new byte[count];
		System.arraycopy(data, 0, dataCopy, 0, count);
		synchronized(linkedList) {
			linkedList.add(new ServerDataEvent(server, socket, dataCopy));
			linkedList.notify();
		}
	}
	
	public void run() {
		ServerDataEvent dataEvent;
		
		while(true) {
			// Wait for data to become available
			synchronized(linkedList) {
				while(linkedList.isEmpty()) {
					try {
						linkedList.wait();
					} catch (InterruptedException e) {
					}
				}
				dataEvent = (ServerDataEvent) linkedList.remove(0);
			}
			
			// Return to sender
			dataEvent.server.send(dataEvent.socket, dataEvent.data);
		}
	}
}