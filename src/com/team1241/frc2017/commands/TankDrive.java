package com.team1241.frc2017.commands;

import com.team1241.frc2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is used to set a default command for the Drivetrain subsystem.
 * This command allows the driver to control the robot using tank drive.
 *
 * @author Kaveesha Siribaddana
 * @since 11/01/17
 */
public class TankDrive extends Command {

	public TankDrive() {
		requires(Robot.drive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		if (Robot.oi.getDriveLeftBumper()) {
			Robot.drive.runLeftDrive(0.5 * (Robot.oi.getDriveLeftY()));
			Robot.drive.runRightDrive(-0.5 * (Robot.oi.getDriveRightY()));
		} else if (Robot.oi.getDriveRightBumper()) {
			Robot.drive.runLeftDrive(0.25 * (Robot.oi.getDriveLeftY()));
			Robot.drive.runRightDrive(-0.25 * (Robot.oi.getDriveRightY()));
		} else {
			Robot.drive.runLeftDrive(Robot.oi.getDriveLeftY());
			Robot.drive.runRightDrive(-Robot.oi.getDriveRightY());
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
