package com.team1241.frc2017.commands;

import com.team1241.frc2017.LedConstants;
import com.team1241.frc2017.Robot;
import com.team1241.frc2017.subsystems.LEDstrips;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LedCommand extends Command {

    public LedCommand() {
       requires(Robot.ledstrips);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.getDriveAButton()){
    		LEDstrips.HopperSetState(LedConstants.HOPPER_GEAR);
    	}
    	else if(Robot.oi.getDriveBButton()){
    		LEDstrips.HopperSetState(LedConstants.HOPPER_BALL);
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
