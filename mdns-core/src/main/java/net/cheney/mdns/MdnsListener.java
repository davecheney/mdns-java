package net.cheney.mdns;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;

public class MdnsListener implements Runnable {

	private final MulticastSocket socket;

	public MdnsListener() throws IOException {
		socket = new MulticastSocket(5353);
		socket.joinGroup(InetAddress.getByName("224.0.0.251"));
	}

	public void run() {
		try {
			for( ;; ) {
				DatagramPacket p = new DatagramPacket(new byte[512], 512);
				socket.receive(p);
				ByteBuffer b = ByteBuffer.wrap(p.getData(), p.getOffset(), p.getLength());
				DNSResponse response = DNSResponseParser.parse(b);
				System.out.println(response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			socket.close();
		}
	}
	
	public static void main(String[] args) throws IOException {
		Thread t = new Thread(new MdnsListener());
		t.run();
	}
}
