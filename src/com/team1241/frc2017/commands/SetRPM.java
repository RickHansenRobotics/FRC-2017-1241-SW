package com.team1241.frc2017.commands;

import com.team1241.frc2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *@author Kaveesha Siribaddana
 *@since 15/01/17
 */
public class SetRPM extends Command {

	private double rpm;
	
    public SetRPM(double rpm){
    	this.rpm = rpm;
    }
    
    public void changeRPM(double rpm){
    	this.rpm = rpm;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.resetPID();	
    	Robot.shooter.setShooterState(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.setRPM(rpm);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.resetPID();
    	Robot.shooter.setShooter(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.resetPID();
    	Robot.shooter.setShooter(0);
    }
}
