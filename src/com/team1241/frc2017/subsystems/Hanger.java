package com.team1241.frc2017.subsystems;

import com.ctre.CANTalon;
import com.team1241.frc2017.ElectricalConstants;
import com.team1241.frc2017.commands.HangerCommand;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Hanger extends Subsystem {

	VictorSP hangMotorLeft;
	Talon hangMotorRight;

	DoubleSolenoid stabilizerPiston;
	boolean wasPressed = false;
	boolean hangStarted = false;

	DigitalInput hangSwitch;

	public Hanger() {
		hangMotorLeft = new VictorSP(ElectricalConstants.LEFT_HANG_MOTOR);
		hangMotorRight = new Talon(ElectricalConstants.RIGHT_HANG_MOTOR);
		
		stabilizerPiston = new DoubleSolenoid(ElectricalConstants.STABILIZER_PISTON_A,
											  ElectricalConstants.STABILIZER_PISTON_B);

		hangSwitch = new DigitalInput(ElectricalConstants.HANGER_LIMIT_SWITCH);

		extendStabilizerPiston();
	}

	public void hangMotor(double input) {
		hangMotorLeft.set(input);
		hangMotorRight.set(-input);
	}

	public void extendStabilizerPiston() {
		stabilizerPiston.set(DoubleSolenoid.Value.kReverse);
	}

	public void retractStabilizerPiston() {
		stabilizerPiston.set(DoubleSolenoid.Value.kForward);
	}

	public boolean limitSwitchIsPressed() {
		if(!hangSwitch.get())
			wasPressed = true;
		return !hangSwitch.get();
	}
	
	public boolean limitEngaged(){
		return wasPressed;
	}
	
	public boolean hangStarted(){
		return hangStarted;
	}
	
	public void hangStarted(boolean state){
		hangStarted = state;
	}

	public void initDefaultCommand() {
		setDefaultCommand(new HangerCommand());
	}
}
