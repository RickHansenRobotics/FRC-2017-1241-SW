package com.team1241.frc2017.subsystems;

import com.ctre.CANTalon;
import com.team1241.frc2017.ElectricalConstants;
import com.team1241.frc2017.commands.HangerCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Hanger extends Subsystem {

	VictorSP hangMotorLeft;
	VictorSP hangMotorRight;

	DoubleSolenoid hangpiston;

	public Hanger() {
		hangMotorLeft = new VictorSP(ElectricalConstants.LEFT_HANG_MOTOR);
		hangMotorRight = new VictorSP(ElectricalConstants.RIGHT_HANG_MOTOR);

		hangpiston = new DoubleSolenoid(ElectricalConstants.HANG_PISTON_A, ElectricalConstants.HANG_PISTON_B);

	}

	public void hangMotor(double input) {
		hangMotorLeft.set(input);
		hangMotorRight.set(-input);
	}

	public void extendHangPiston() {
		hangpiston.set(DoubleSolenoid.Value.kForward);
	}

	public void retractHangPiston() {
		hangpiston.set(DoubleSolenoid.Value.kReverse);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new HangerCommand());
	}
}
