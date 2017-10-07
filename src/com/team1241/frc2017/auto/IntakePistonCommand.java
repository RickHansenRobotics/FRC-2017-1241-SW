package com.team1241.frc2017.auto;

import com.team1241.frc2017.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class IntakePistonCommand extends InstantCommand {

	private boolean actuate;
	
    public IntakePistonCommand(boolean actuate) {
        super();
        this.actuate = actuate;
       // requires(Robot.intake);
    }

    // Called once when the command executes
    protected void initialize() {
//    	if(actuate)
//    		Robot.intake.extendIntake();
//    	else
//    		Robot.intake.retractIntake();
//    }

}}
