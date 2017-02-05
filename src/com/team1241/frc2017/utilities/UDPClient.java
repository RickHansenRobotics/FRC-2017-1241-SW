package com.team1241.frc2017.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UDPClient extends Thread {
	public static String lastDataReceived = "";
	public static String data;

	protected DatagramSocket socket = null;
	protected BufferedReader in = null;
	protected boolean moreQuotes = true;
	InetAddress addr;

	public UDPClient() throws IOException {
		this("udpClient");
	}

	public UDPClient(String name) throws IOException {
		super(name);
		addr = InetAddress.getByName("10.12.41.105");
		socket = new DatagramSocket(22);
	}

	public void run() {
		
		byte[] buf = new byte[256];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		while (moreQuotes) {
			try {
				// receive request
				packet.setLength(buf.length);
				SmartDashboard.putString("in thread", "before");
				SmartDashboard.putString("local", addr.getHostAddress());
				socket.receive(packet);
				SmartDashboard.putString("in thread", "next");
				byte[] data = packet.getData();
				lastDataReceived = new String(data, 0, packet.getLength());
				// SmartDashboard.putString("last Data Received",
				// lastDataReceived);
				// figure out response
				//visionData = gson.fromJson(lastDataReceived, VisionData.class);
				//visionData.whenRecieved = System.currentTimeMillis();

				SmartDashboard.putString("in thread", "hi");
				SmartDashboard.putString("DATA FROM PI:", lastDataReceived);
			} catch (IOException e) {
				SmartDashboard.putString("in thread", "inside thread error");
				SmartDashboard.putData("error", (Sendable) e);
				moreQuotes = true;
			}
		}
		socket.close();
	}

//	public String toHex(String arg) {
//		return String.format("%040x", new BigInteger(1, arg.getBytes()));
//	}

}