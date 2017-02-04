package com.team1241.frc2017.subsystems;

import com.ctre.CANTalon;
import com.team1241.frc2017.ElectricalConstants;
import com.team1241.frc2017.commands.HangerCommand;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Hanger extends Subsystem {

	CANTalon hangMotorLeft;
	CANTalon hangMotorRight;

	DoubleSolenoid hangpiston;
	
	DigitalInput optical;

	public Hanger() {
		hangMotorLeft = new CANTalon(ElectricalConstants.LEFT_HANG_MOTOR);
		hangMotorRight = new CANTalon(ElectricalConstants.RIGHT_HANG_MOTOR);

		hangpiston = new DoubleSolenoid(ElectricalConstants.HANG_PISTON_A, ElectricalConstants.HANG_PISTON_B);

		optical = new DigitalInput(ElectricalConstants.OPTICAL_SENSOR_HANGER);
	}

	public void hangMotor(double input) {
		hangMotorLeft.set(input);
		hangMotorRight.set(input);
	}

	public void extendHangPiston() {
		hangpiston.set(DoubleSolenoid.Value.kForward);
	}

	public void retractHangPiston() {
		hangpiston.set(DoubleSolenoid.Value.kReverse);
	}
	
	public boolean getOptic() {
		return optical.get();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new HangerCommand());
	}
}
