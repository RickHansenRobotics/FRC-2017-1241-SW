package com.team1241.frc2017.commands;

import com.team1241.frc2017.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ResetSensorsCommand extends InstantCommand {

    public ResetSensorsCommand() {
        super();
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.drive.reset();
    }

}
