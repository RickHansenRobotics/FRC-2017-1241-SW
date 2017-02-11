package com.team1241.frc2017.commands;

import com.team1241.frc2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ConveyorCommand extends Command {

	public ConveyorCommand() {
		requires(Robot.conveyor);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		if (Robot.oi.getToolAButton()) {
			Robot.conveyor.conveyorMotor(1);
			Robot.conveyor.agitatorMotor(1);
		} else if (Robot.oi.getToolXButton()) {
			Robot.conveyor.conveyorMotor(-1);
			Robot.conveyor.agitatorMotor(-1);
		} else if (Robot.oi.getToolBButton()) {
			Robot.conveyor.conveyorMotor(0.5);
			Robot.conveyor.agitatorMotor(0.5);
		} else if (Robot.oi.getToolYButton()) {
			Robot.conveyor.conveyorMotor(-0.5);
			Robot.conveyor.agitatorMotor(-0.5);
		} else {
			Robot.conveyor.conveyorMotor(0);
			Robot.conveyor.agitatorMotor(0);
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
