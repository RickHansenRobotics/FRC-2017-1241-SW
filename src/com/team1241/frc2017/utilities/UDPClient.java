package com.team1241.frc2017.utilities;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.*;

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
		addr = InetAddress.getByName("10.12.41.1");
		socket = new DatagramSocket(5880, addr);
	}

	public void run() {
		System.out.println("thread start");
		byte[] buf = new byte[256];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		while (moreQuotes) {
			try {
				// receive request
				packet.setLength(buf.length);
				SmartDashboard.putString("in thread", "before");
				SmartDashboard.putString("local", addr.getHostAddress());
				socket.receive(packet);
				byte[] data = packet.getData();
				lastDataReceived = new String(data, 0, packet.getLength());
				// SmartDashboard.putString("last Data Received",
				// lastDataReceived);
				System.out.println("'" + lastDataReceived + "'");
				// figure out response
				//visionData = gson.fromJson(lastDataReceived, VisionData.class);
				//visionData.whenRecieved = System.currentTimeMillis();

				SmartDashboard.putString("in thread", "hi");
			} catch (IOException e) {
				SmartDashboard.putString("in thread", "inside thread error");
				e.printStackTrace();
				moreQuotes = false;
			}
		}
		socket.close();
	}

	public String toHex(String arg) {
		return String.format("%040x", new BigInteger(1, arg.getBytes()));
	}

}