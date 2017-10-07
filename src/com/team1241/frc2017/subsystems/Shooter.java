package com.team1241.frc2017.subsystems;

import com.ctre.CANTalon;
import com.team1241.frc2017.ElectricalConstants;
import com.team1241.frc2017.NumberConstants;
import com.team1241.frc2017.commands.ShooterCommand;
import com.team1241.frc2017.pid.PIDController;
import com.team1241.frc2017.utilities.LineRegression;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Kaveesha Siribaddana
 * @since 14/01/17
 */
public class Shooter extends Subsystem {

	CANTalon rightMotor;
	CANTalon leftMotor;

	Counter optical;

	public PIDController shooterPID;

	boolean shooterState;

	LineRegression calcLine = new LineRegression();

	private double kForward;
	private double bForward;

	double prev = 0;

	public Shooter() {

		rightMotor = new CANTalon(ElectricalConstants.RIGHT_SHOOTER_MOTOR);
		leftMotor = new CANTalon(ElectricalConstants.LEFT_SHOOTER_MOTOR);

		optical = new Counter();
		optical.setUpSource(ElectricalConstants.OPTICAL_SENSOR_SHOOTER);
		optical.setUpDownCounterMode();
		optical.setDistancePerPulse(1);

		shooterPID = new PIDController(NumberConstants.pShooter, NumberConstants.iShooter, NumberConstants.dShooter);

		shooterState = false;

		calcLine.setValues(NumberConstants.RPMS_SHOOTER, NumberConstants.POWERS_SHOOTER);
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
		double output = shooterPID.calcPID(rpm, getRPM(rpm), 50);

		setShooter(rpm * kForward + bForward + output);
	}

	public void setShooter(double input) {
		rightMotor.set(-input);
		leftMotor.set(input);
	}

	public int getOptic() {
		return optical.get();
	}

	public double getRPM(double setRPM) {
		// If rpm is greater than the previous by at most 1000 AND the rpm is
		// greater than the setRPM, then return the previous rpm
		// Else return the current rpm
		if (optical.getRate() * 60 > (prev + 1000) && optical.getRate() * 60 > (setRPM + 500)) {
			return prev;
		} else {
			prev = optical.getRate() * 60;
			return prev;
		}
	}

	public double getSlope() {
		return kForward;
	}

	public double getIntercept() {
		return bForward;
	}

	// SHOOTER PID

	public void resetPID() {
		shooterPID.resetPID();
	}

	public void changeGains(double p, double i, double d) {
		shooterPID.changePIDGains(p, i, d);
	}
}
