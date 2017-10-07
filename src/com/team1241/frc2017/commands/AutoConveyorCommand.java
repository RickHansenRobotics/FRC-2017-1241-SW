package com.team1241.frc2017.commands;

import com.team1241.frc2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoConveyorCommand extends Command {

	private int state;
	private double timeout;

	public AutoConveyorCommand(int state, double timeout) {
		this.state = state;
		this.timeout = timeout;
		requires(Robot.conveyor);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if(timeout != -1)
			setTimeout(timeout);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (state == 2) {
			
			//rpm.cancel();
			//reverseRPM.start();
			Robot.conveyor.setConveyorPower(0.45);
		} 
		else if(state == 1){
			
			//reverseRPM.cancel();
			//rpm.start();
			Robot.conveyor.setConveyorPower(-1);
		} else {
			
			Robot.conveyor.setConveyorPower(0);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(timeout == -1)
			return true;
		else
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
