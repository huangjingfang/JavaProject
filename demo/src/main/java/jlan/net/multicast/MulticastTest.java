package jlan.net.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MulticastSocket multicastSocket = new MulticastSocket(8899);
		multicastSocket.setTimeToLive(5);
		byte[] data = "组播测试".getBytes();
		InetAddress address = InetAddress.getByName("224.0.0.0");
		multicastSocket.joinGroup(address);
		
		DatagramPacket datagramPacket = new DatagramPacket(data, data.length, address, 8899);
		multicastSocket.send(datagramPacket);
		multicastSocket.close();
	}

}
