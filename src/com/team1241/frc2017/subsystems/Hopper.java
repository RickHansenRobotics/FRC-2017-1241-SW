package com.team1241.frc2017.subsystems;

import com.team1241.frc2017.ElectricalConstants;
import com.team1241.frc2017.commands.HopperCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Hopper extends Subsystem {
    
	//*********************************************************************************************
	//*********************************** VARIABLES ***********************************************
	//*********************************************************************************************
	
	DoubleSolenoid hopper;
	DoubleSolenoid gear;
	
	public Hopper(){
	
	hopper = new DoubleSolenoid(ElectricalConstants.HOPPER_PISTON_A,
								ElectricalConstants.HOPPER_PISTON_B);
	
	gear = new DoubleSolenoid(ElectricalConstants.GEAR_PISTON_A,
							  ElectricalConstants.GEAR_PISTON_B);
	
	}
	public void ExtendHopper(){
		hopper.set(DoubleSolenoid.Value.kForward);
	}
	public void RetractHopper(){
		hopper.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void ExtendGear(){
		gear.set(DoubleSolenoid.Value.kForward);
	}
	public void RetractGear(){
		gear.set(DoubleSolenoid.Value.kReverse);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new HopperCommand());
    }
    
}

