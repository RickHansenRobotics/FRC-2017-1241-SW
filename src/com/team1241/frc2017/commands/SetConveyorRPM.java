package com.team1241.frc2017.commands;

import com.team1241.frc2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetConveyorRPM extends Command {

	private double rpm;
	
    public SetConveyorRPM(double rpm){
    	this.rpm = rpm;  
    }
    
    public void changeConveyorRPM(double rpm){
    	this.rpm = rpm;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.conveyor.resetPID();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.shooter.changeGains(Robot.p, Robot.i, Robot.d);
		Robot.conveyor.setRPM(rpm);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.conveyor.resetPID();
		Robot.conveyor.setConveyorPower(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.conveyor.resetPID();
		Robot.conveyor.setConveyorPower(0);
    }
}
