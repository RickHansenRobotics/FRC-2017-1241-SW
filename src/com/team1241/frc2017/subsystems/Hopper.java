package com.team1241.frc2017.subsystems;

import com.team1241.frc2017.commands.HopperCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Hopper extends Subsystem {

	DoubleSolenoid hopper;
	DoubleSolenoid gear;

	public Hopper() {

		//hopper = new DoubleSolenoid(ElectricalConstants.HOPPER_PISTON_A, ElectricalConstants.HOPPER_PISTON_B);

	}

	public void extendHopper() {
		hopper.set(DoubleSolenoid.Value.kReverse);
	}

	public void retractHopper() {
		hopper.set(DoubleSolenoid.Value.kForward);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new HopperCommand());
	}

}
