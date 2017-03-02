package com.team1241.frc2017.commands;

import com.team1241.frc2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HangerCommand extends Command {

	public HangerCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.hanger);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		/*
		 * if(Robot.oi.getToolStartButton()) Robot.hanger.hangMotor(1); else
		 * Robot.hanger.hangMotor(0);
		 */
		if (Robot.hanger.limitSwitchIsPressed())
			Robot.hanger.extendStabilizerPiston();

		if (Robot.oi.getToolStartButton()) {
			new HangSequence().start();
			Robot.hanger.hangMotor(0.5);
		} else if (Robot.oi.getToolStartButton() && Robot.hanger.hangerEngaged())
			Robot.hanger.hangMotor(1);
		else {
			Robot.hanger.hangMotor(0);
		}

		Robot.hanger.hangMotor(Math.abs(Robot.oi.getToolRightY()));

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
