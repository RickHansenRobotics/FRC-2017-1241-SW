package com.team1241.frc2017.utilities;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Target {

	NetworkTable server;

	private int numTargets = 0;
	private double[] box1 = new double[8];
	private double[] box2 = new double[8];

	public Target() {
		server = NetworkTable.getTable("SmartDashboard");

		numTargets = (int) server.getNumber("Number of Targets", 0);

		if (numTargets == 2) {
			box1 = server.getNumberArray("Box1", new double[] { 0, 0 });
			box2 = server.getNumberArray("Box2", new double[] { 0, 0 });
		} else if (numTargets == 1)
			box1 = server.getNumberArray("Box1", new double[] { 0, 0 });
		else {
			box1 = server.getNumberArray("Box1", new double[] { -1, -1 });
			box2 = server.getNumberArray("Box2", new double[] { -1, -1 });
		}
	}

	public void updateCoordinates() {
		numTargets = (int) server.getNumber("Number of Targets", 0);

		if (numTargets == 2) {
			box1 = server.getNumberArray("Box1", new double[] { 0, 0 });
			box2 = server.getNumberArray("Box2", new double[] { 0, 0 });
		} else if (numTargets == 1)
			box1 = server.getNumberArray("Box1", new double[] { 0, 0 });
		else {
			box1 = server.getNumberArray("Box1", new double[] { -1, -1 });
			box2 = server.getNumberArray("Box2", new double[] { -1, -1 });
		}
	}

	public double getCenterX() {
		double avg = -1;
		if (numTargets == 2)
			avg = (box1[0] + box1[2] + box1[4] + box1[6] + box2[0] + box2[2] + box2[4] + box2[6]) / 8;
		else if (numTargets == 1)
			avg = (box1[0] + box1[2] + box1[4] + box1[6]) / 4;

		return avg;
	}
	
	public double getTopY(){
		double avg = -1;
		if (numTargets == 2)
			avg = (box1[1] + box1[3] + box2[1] + box2[3]) / 2;
		else if (numTargets == 1)
			avg = (box1[1] + box1[3]) / 2;

		return avg;
	}
	
	public int numOfTargets(){
		return numTargets;
	}
}
