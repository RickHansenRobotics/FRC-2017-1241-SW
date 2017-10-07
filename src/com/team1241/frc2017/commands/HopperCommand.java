package com.team1241.frc2017.commands;

import com.team1241.frc2017.Robot;
import com.team1241.frc2017.auto.AutoCloseSequence;
import com.team1241.frc2017.auto.AutoOpenSequence;
import com.team1241.frc2017.auto.HopperPistonCommand;
import com.team1241.frc2017.utilities.ToggleBoolean;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HopperCommand extends Command {

	ToggleBoolean toggle = new ToggleBoolean();
	ToggleBoolean toggleHopper = new ToggleBoolean();

	public HopperCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.hopper);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//FEEDER
		toggle.set(Robot.oi.getToolLeftTrigger());
		/*if (!Robot.hanger.hangStarted()) {
			if (toggle.get()) {
				Robot.hopper.extendHopper();
			} else {
				Robot.hopper.retractHopper();
			}*/
		
		/*if (Robot.oi.getDriveLeftBumper() || Robot.oi.getToolLeftTrigger()) {
			Robot.hopper.retractHopper();
		} else if (!Robot.oi.getDriveLeftBumper() || !Robot.oi.getToolRightTrigger()) {
			Robot.hopper.extendHopper();
		} else {
			Robot.hopper.extendHopper();
		}
		*/
		if (Robot.oi.getDriveLeftBumper() || Robot.oi.getToolLeftTrigger()) {
			Robot.gearMech.extendGearMech();
		} else if (!Robot.oi.getDriveLeftBumper() || !Robot.oi.getToolRightTrigger()) {
			Robot.gearMech.retractGearMech();
		} else {
			Robot.gearMech.retractGearMech();
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
