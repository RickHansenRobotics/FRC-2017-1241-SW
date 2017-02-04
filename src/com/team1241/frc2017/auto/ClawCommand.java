package com.team1241.frc2017.auto;

import com.team1241.frc2017.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ClawCommand extends InstantCommand {

	private boolean actuate;
	
    public ClawCommand(boolean actuate) {
        super();
        this.actuate = actuate;
    }

    // Called once when the command executes
    protected void initialize() {
    	if(actuate)
    		Robot.shooter.openClaw();
    	else
    		Robot.shooter.closeClaw();
    }

}
