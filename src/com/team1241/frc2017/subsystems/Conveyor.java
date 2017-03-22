package com.team1241.frc2017.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.FeedbackDeviceStatus;
import com.team1241.frc2017.ElectricalConstants;
import com.team1241.frc2017.NumberConstants;
import com.team1241.frc2017.commands.ConveyorCommand;
import com.team1241.frc2017.pid.PIDController;
import com.team1241.frc2017.utilities.LineRegression;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Conveyor extends Subsystem {

	// Declaring the different Victors/motors being used in the Conveyor class
	// e.g. agitator and conveyor.
	CANTalon agitatorFeeder;
	CANTalon agitatorHopper;

	CANTalon conveyorMaster;
	CANTalon conveyorSlave;

	// Talon conveyor1;
	// Talon conveyor2;

	// Declaring the piston being used e.g. the claw piston.
	DoubleSolenoid claw;

	private boolean conveyorEncoderConnected = false;

	// Declaring the PIDcontroller for the conveyor.
	public PIDController conveyorPID;

	// LineRegression being declared to be used for the conveyor.
	LineRegression calcline = new LineRegression();

	// Declaring variables to be used for the PID
	private double kForward;
	private double bForward;

	public Conveyor() {

		// Initializing the victors and connecting it to the physical motors.
		agitatorFeeder = new CANTalon(ElectricalConstants.AGITATOR_MOTOR);
		agitatorHopper = new CANTalon(ElectricalConstants.AGITATOR_HOPPER);

		conveyorMaster = new CANTalon(ElectricalConstants.CONVEYOR_MOTOR1);
		conveyorMaster.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		conveyorMaster.reverseSensor(false);

		conveyorSlave = new CANTalon(ElectricalConstants.CONVEYOR_MOTOR2);

		// conveyor1 = new Talon(ElectricalConstants.CONVEYOR_MOTOR1);
		// conveyor2 = new Talon(ElectricalConstants.CONVEYOR_MOTOR2);

		// Initializing the piston and connecting it to the physical pneumatic
		// piston.

		// Initializing the PIDController for the Conveyor.
		conveyorPID = new PIDController(NumberConstants.pConveyor, NumberConstants.iConveyor,
				NumberConstants.dConveyor);

		// Calculating the slope and the point of intersection between the graph
		// of the conveyors RPM and the amount of power going into the motor.
		calcline.setValues(NumberConstants.RPMS_CONVEYOR, NumberConstants.POWERS_CONVEYOR);
		kForward = calcline.getSlope(); // Calculating Slope
		bForward = calcline.getIntercept(); // Calculating The Point of
											// Intersection.

		FeedbackDeviceStatus conveyorMasterStatus = conveyorMaster
				.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative);
		FeedbackDeviceStatus conveyorSlaveStatus = conveyorMaster
				.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative);
		
		switch (conveyorMasterStatus) {
		case FeedbackStatusPresent:
			conveyorEncoderConnected = true;
			break;
		case FeedbackStatusNotPresent:
			break;
		case FeedbackStatusUnknown:
			break;
		}
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ConveyorCommand());
	}

	// Function to control the agitator.
	public void agitatorFeeder(double input) {
		agitatorFeeder.set(input);
	}

	public void agitatorHopper(double input) {
		agitatorHopper.set(-input);
	}

	// Function to control the Conveyor
	public void setConveyorPower(double input) {
		conveyorMaster.set(input);
		conveyorSlave.set(-input);
	}

	// Function to control the Piston

	// Function to get the distance value from the encoder.
	public double getConveyorEncoder() {
		return conveyorMaster.getPosition();
	}

	// Function to get the feed rate of the conveyor from the encoder.
	public double getConveyorSpeed() {
		return conveyorMaster.getSpeed();
	}

	// Function to reset the encoder on the conveyor.
	public void resetConveyorEncoder() {
		conveyorMaster.setPosition(0);
		conveyorSlave.setPosition(0);
	}

	// Function to set and control or call the RPM of the conveyor.
	public void setRPM(double RPM) {
		double output = conveyorPID.calcPID(RPM, getConveyorSpeed(), 50);
		setConveyorPower(RPM * kForward + bForward + output);
	}
	
	public void resetPID(){
		conveyorPID.resetPID();
	}

}