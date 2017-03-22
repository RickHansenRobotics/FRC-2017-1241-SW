package com.team1241.frc2017.commands;

import com.team1241.frc2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoConveyorCommand extends Command {

	private boolean state;
	private double timeout;

	public AutoConveyorCommand(boolean state, double timeout) {
		this.state = state;
		this.timeout = timeout;
		requires(Robot.conveyor);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		setTimeout(timeout);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (state) {
			Robot.conveyor.agitatorFeeder(-0.5);
			Robot.conveyor.agitatorHopper(0.5);
			Robot.conveyor.setConveyorPower(-0.4);
		} else {
			Robot.conveyor.agitatorFeeder(0);
			Robot.conveyor.agitatorHopper(0);
			Robot.conveyor.setConveyorPower(0);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
