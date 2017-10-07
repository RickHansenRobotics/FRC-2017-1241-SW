package com.team1241.frc2017.commands;

import com.team1241.frc2017.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class AutoIntakeCommand extends InstantCommand {

	private boolean state;
	
    public AutoIntakeCommand(boolean state) {
        super();
        this.state = state;
        //requires(Robot.intake);
    }

    // Called once when the command executes
    protected void initialize() {
//    	if(state){
//    		Robot.intake.setIntakeSpeed(1);
//    	}
//    	else
//    		Robot.intake.setIntakeSpeed(0);
//    }

    }}
