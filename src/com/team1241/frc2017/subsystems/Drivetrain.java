package com.team1241.frc2017.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.FeedbackDeviceStatus;
import com.ctre.CANTalon.TalonControlMode;
import com.team1241.frc2017.ElectricalConstants;
import com.team1241.frc2017.NumberConstants;
import com.team1241.frc2017.commands.TankDrive;
import com.team1241.frc2017.pid.PIDController;
import com.team1241.frc2017.utilities.Nav6;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Kaveesha Siribaddana
 * @since 11/01/17
 *
 */
public class Drivetrain extends Subsystem {

	/** Drive Talons */
	private CANTalon leftMaster;
	private CANTalon leftSlave;
	private CANTalon rightMaster;
	private CANTalon rightSlave;

	/** Encoders on the drive */
	private boolean leftEncoderConnected = false;
	private boolean rightEncoderConnected = false;

	/** Gyro on the drive */
	private SerialPort serialPort;
	private Nav6 gyro;

	/** The drive PID controller. */
	private PIDController drivePID;

	/** The gyro PID conteroller. */
	private PIDController gyroPID;

	/**
	 * Instantiates a new drivetrain subsystem, this includes initializing all
	 * components related to the subsystem
	 */
	public Drivetrain() {
		try {
			serialPort = new SerialPort(57600, SerialPort.Port.kOnboard);

			// You can add a second parameter to modify the
			// update rate (in hz) from 4 to 100. The default is 100.
			// If you need to minimize CPU load, you can set it to a
			// lower value, as shown here, depending upon your needs.

			// You can also use the IMUAdvanced class for advanced
			// features.

			byte update_rate_hz = 50;
			gyro = new Nav6(serialPort, update_rate_hz);

			if (!gyro.isCalibrating()) {
				Timer.delay(0.3);
				gyro.zeroYaw();
			}
		} catch (Exception e) {
			gyro = null;
		}

		// Initialize Talons
		leftMaster = new CANTalon(ElectricalConstants.LEFT_DRIVE_FRONT);
		leftMaster.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		leftMaster.reverseSensor(true);

		leftSlave = new CANTalon(ElectricalConstants.LEFT_DRIVE_BACK);
		leftSlave.changeControlMode(TalonControlMode.Follower);
		leftSlave.set(ElectricalConstants.LEFT_DRIVE_FRONT);

		rightMaster = new CANTalon(ElectricalConstants.RIGHT_DRIVE_FRONT);
		rightMaster.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		rightMaster.reverseSensor(false);

		rightSlave = new CANTalon(ElectricalConstants.RIGHT_DRIVE_BACK);
		//rightSlave.changeControlMode(TalonControlMode.Follower);
		//rightSlave.set(ElectricalConstants.RIGHT_DRIVE_FRONT);

		FeedbackDeviceStatus leftStatus = leftMaster.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative);
		FeedbackDeviceStatus rightStatus = rightMaster.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative);

		switch (leftStatus) {
		case FeedbackStatusPresent:
			leftEncoderConnected = true;
			break;
		case FeedbackStatusNotPresent:
			break;
		case FeedbackStatusUnknown:
			break;
		}

		switch (rightStatus) {
		case FeedbackStatusPresent:
			rightEncoderConnected = true;
			break;
		case FeedbackStatusNotPresent:
			break;
		case FeedbackStatusUnknown:
			break;
		}

		// Initialize PID controllers
		drivePID = new PIDController(NumberConstants.pDrive, NumberConstants.iDrive, NumberConstants.dDrive);
		gyroPID = new PIDController(NumberConstants.pGyro, NumberConstants.iGyro, NumberConstants.dGyro);
		
		rightMaster.setProfile(0);
		rightMaster.setPID(0.01, 0, 0);
		rightMaster.setF(0.2670508);
		
		leftMaster.setProfile(0);
		leftMaster.setPID(0.01, 0, 0);
		leftMaster.setF(0.2670508);
		
		resetEncoders();
		resetGyro();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new TankDrive());
	}

	public void runLeftDrive(double input) {
		leftMaster.set(input);
	}

	public void runRightDrive(double input) {
		rightMaster.set(input);
		rightSlave.set(input);
	}
	
	public CANTalon getRightMaster(){
		return rightMaster;
	}
	
	public CANTalon getLeftMaster(){
		return leftMaster;
	}
	
	public void motionProfileMode(){
		rightMaster.changeControlMode(TalonControlMode.MotionProfile);
		leftMaster.changeControlMode(TalonControlMode.MotionProfile);
	}
	
	public void voltageMode(){
		rightMaster.changeControlMode(TalonControlMode.PercentVbus);
		leftMaster.changeControlMode(TalonControlMode.PercentVbus);
	}

	public void driveSetpoint(double setPoint, double speed, double setAngle, double tolerance) {
		double output = drivePID.calcPID(setPoint, getAverageDistance(), tolerance);
		double angle = gyroPID.calcPID(setAngle, getYaw(), tolerance);
		SmartDashboard.putNumber("PID OUTPUT", angle);
		runLeftDrive((-output - angle) * speed);
		runRightDrive((output - angle) * speed);
	}

	public void turnDrive(double setAngle, double speed, double tolerance) {
		double angle = gyroPID.calcPID(setAngle, getYaw(), tolerance);

		runLeftDrive(-angle * speed);
		runRightDrive(-angle * speed);
	}
	
	public void driveAngle(double setAngle, double speed) {
		double angle = gyroPID.calcPID(setAngle, getYaw(), 1);

		runLeftDrive(speed + angle);
		runRightDrive(-speed + angle);
	}
	
	/**
	 * Converts the pixel offset from the center of the image to degrees, which
	 * is then used for turning the turret
	 *
	 * @param pixel
	 *            The x coordinate pixel from the image. Ranging from 0 to 640
	 *            on a 480x640 image
	 * @return the double
	 */
	public double pixelToDegree(double pixel) {
		return Math.toDegrees(Math.atan(((pixel - 320) * Math.tan(Math.toRadians(31.81))) / 320));
	}
	
	public boolean drivePIDDone() {
		return drivePID.isDone();
	}
	
	public boolean gyroPIDDone() {
		return gyroPID.isDone();
	}
	
	public void resetPID(){
		drivePID.resetPID();
		gyroPID.resetPID();
	}

	// ENCODER FUNCTIONS

	public double getLeftDriveEncoder() {
		return leftMaster.getPosition()*ElectricalConstants.ROTATIONS_TO_INCHES;
	}

	public double getRightDriveEncoder() {
		return rightMaster.getPosition()*ElectricalConstants.ROTATIONS_TO_INCHES;
	}

	public double getAverageDistance() {
		return (getLeftDriveEncoder() + getRightDriveEncoder()) / 2;
	}

	public boolean isLeftEncoderConnected() {
		return leftEncoderConnected;
	}

	public boolean isRightEncoderConnected() {
		return rightEncoderConnected;
	}

	public void resetEncoders() {
		leftMaster.setPosition(0);
		rightMaster.setPosition(0);
	}

	// GYRO FUNCTIONS

	public boolean gyroConnected() {
		return gyro.isConnected();
	}

	public boolean gyroCalibrating() {
		return gyro.isCalibrating();
	}

	public double getYaw() {
		return gyro.getYaw();
	}

	public double getPitch() {
		return gyro.getPitch();
	}

	public double getRoll() {
		return gyro.getRoll();
	}

	public double getCompassHeading() {
		return gyro.getCompassHeading();
	}

	public void resetGyro() {
		gyro.zeroYaw();
	}

	public void reset() {
		resetEncoders();
		resetGyro();
	}
}
