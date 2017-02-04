package com.team1241.frc2017.subsystems;

import com.ctre.CANTalon;
import com.team1241.frc2017.ElectricalConstants;
import com.team1241.frc2017.NumberConstants;
import com.team1241.frc2017.commands.ShooterCommand;
import com.team1241.frc2017.pid.PIDController;
import com.team1241.frc2017.utilities.LineRegression;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Kaveesha Siribaddana
 * @since 14/01/17
 */
public class Shooter extends Subsystem {

	CANTalon rightMotor;
	CANTalon leftMotor;

	Counter optical;
	
	DoubleSolenoid claw;

	public PIDController shooterPID;

	boolean shooterState;

	LineRegression calcLine = new LineRegression();

	private double kForward;
	private double bForward;

	public Shooter() {
		rightMotor = new CANTalon(ElectricalConstants.RIGHT_SHOOTER_MOTOR);
		leftMotor = new CANTalon(ElectricalConstants.LEFT_SHOOTER_MOTOR);

		optical = new Counter();
		optical.setUpSource(ElectricalConstants.OPTICAL_SENSOR_SHOOTER);
		optical.setUpDownCounterMode();
		optical.setDistancePerPulse(1);
		
		claw = new DoubleSolenoid(ElectricalConstants.CLAW_PISTON_A, ElectricalConstants.CLAW_PISTON_B);

		shooterPID = new PIDController(NumberConstants.pShooter, NumberConstants.iShooter, NumberConstants.dShooter);

		shooterState = false;

		calcLine.setValues(NumberConstants.RPMS_SHOOTER, NumberConstants.RPMS_SHOOTER);
		kForward = calcLine.getSlope();
		bForward = calcLine.getIntercept();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ShooterCommand());
	}

	// SHOOTER COMMANDS

	public boolean getShooterState() {
		return shooterState;
	}

	public void setShooterState(boolean state) {
		shooterState = state;
	}

	public void setRPM(double rpm) {
		double output = shooterPID.calcPID(rpm, getRPM(), 50);

		setShooter(rpm * kForward + bForward + output);
	}

	public void setShooter(double input) {
		rightMotor.set(input);
		leftMotor.set(-input);
	}

	public int getOptic() {
		return optical.get();
	}

	public double getRPM() {
		return optical.getRate() * 60;
	}
	
	public void openClaw() {
		claw.set(DoubleSolenoid.Value.kForward);
	}

	// Function to control the Piston
	public void closeClaw() {
		claw.set(DoubleSolenoid.Value.kReverse);
	}

	// SHOOTER PID

	public void resetPID() {
		shooterPID.resetPID();
	}
}
