package com.team1241.frc2017.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WaitToStartCommand extends Command {

	private Command command;
	private double wait;
	
    public WaitToStartCommand(Command command, double wait) {
        this.command = command;
        this.wait = wait;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(wait);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(isTimedOut())
    		command.start();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
