package com.team1241.frc2017.commands;

import com.team1241.frc2017.NumberConstants;
import com.team1241.frc2017.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Kaveesha Siribaddana
 * @since 15/01/17
 */
public class ShooterCommand extends Command {

	private SetRPM rpm;

	public ShooterCommand() {
		requires(Robot.shooter);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		rpm = new SetRPM(Robot.rpm);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.oi.getToolRightBumper()) {
			rpm.start();
			//Robot.shooter.setShooter(Robot.power);
			//Robot.shooter.openClaw();
		} else {
			rpm.cancel();
			//Robot.shooter.setShooter(0);
			//Robot.shooter.closeClaw();
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
