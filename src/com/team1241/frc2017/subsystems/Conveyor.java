package com.team1241.frc2017.subsystems;

import com.team1241.frc2017.ElectricalConstants;
import com.team1241.frc2017.NumberConstants;
import com.team1241.frc2017.pid.PIDController;
import com.team1241.frc2017.utilities.LineRegression;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Conveyor extends Subsystem {
    
    // NEED TO FIX COMMAND FOR CONVEYOR>
	
	//*********************************************************************************************
	//*********************************** VARIABLES ***********************************************
	//*********************************************************************************************
	
	//Declaring the different Victors/motors being used in the Conveyor class e.g. agitator and conveyor.
	Victor agitator;		
	Victor conveyor;
	
	//Declaring the piston being used e.g. the claw piston.
	DoubleSolenoid claw;
	
	//Declaring the encoder.
	Encoder conveyorEncoder;
	
	//Declaring the PIDcontroller for the conveyor.
	public PIDController conveyorPID;
	
	//LineRegression being declared to be used for the conveyor.
	LineRegression calcline = new LineRegression();
	
	// Declaring variables to be used for the PID 
	private double kForward;
	private double bForward;
	
	
	public Conveyor(){
		
		//*********************************************************************************************
		//********************************** INITIALIZATING  ******************************************
		//*********************************************************************************************
		
		// Initializing the victors and connecting it to the physical motors.
		agitator = new Victor(ElectricalConstants.AGITATOR_MOTOR);
		conveyor = new Victor(ElectricalConstants.CONVEYOR_MOTOR);
		
		//Initializing the piston and connecting it to the physical pneumatic piston.
		claw = new DoubleSolenoid(ElectricalConstants.CLAW_PISTON_A,
								  ElectricalConstants.CLAW_PISTON_B);
		
		// Initializing the Encoder and connecting it to the physical encoder.
		conveyorEncoder = new Encoder(ElectricalConstants.CONVEYOR_ENCODER_A,
						   ElectricalConstants.CONVEYOR_ENCODER_B,
						   ElectricalConstants.conveyorEncoderReverse,
						   Encoder.EncodingType.k4X);
		
		conveyorEncoder.setDistancePerPulse(ElectricalConstants.conveyorEncoderDistPerTick);
		
		// Initializing the PIDController for the Conveyor.
		conveyorPID = new PIDController(NumberConstants.pConveyor,
										NumberConstants.iConveyor,
										NumberConstants.dConveyor);
		
		//Calculating the slope and the point of intersection between the graph of the conveyors RPM and the amount of power going into the motor.
		calcline.setValues(NumberConstants.RPMS_CONVEYOR,NumberConstants.POWERS_CONVEYOR);
		kForward = calcline.getSlope();      // Calculating Slope
		bForward = calcline.getIntercept();  // Calculating The Point of Intersection.
		
		
		
	}
	//*********************************************************************************************
	//*********************************** FUNCTIONS ***********************************************
	//*********************************************************************************************		
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
	}
    
    // Function to control the agitator. 
    public void agitatorMotor(double input){
    	agitator.set(input);
    }
    
    // Function to control the Conveyor
    public void conveyorMotor(double input){
    	conveyor.set(input);
    }
    
    // Function to control the Piston
    public void openClaw(){
    	claw.set(DoubleSolenoid.Value.kForward);
    }
    
    // Function to control the Piston
    public void closeClaw(){
    	claw.set(DoubleSolenoid.Value.kReverse);
    }
    
    // Function to get the distance value from the encoder.
    public double getConveyorEncoder(){
    	return conveyorEncoder.getDistance();
    }
    
    // Function to get the feed rate of the conveyor from the encoder.
    public double getConveyorRate(){
    	return conveyorEncoder.getRate() * 60;
    }
    
    // Function to reset the encoder on the conveyor.
    public void resetConveyorEncoder(){
    	conveyorEncoder.reset();
    }
    
    // Function to set and control or call the RPM of the conveyor. 
    public void setRPM(double RPM){
    	double output = conveyorPID.calcPID(RPM, getConveyorRate(), 50);
    	conveyorMotor(RPM * kForward + bForward + output);    	
    }
    
    
}