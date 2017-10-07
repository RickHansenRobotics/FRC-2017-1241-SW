package com.team1241.frc2017.auto;

import com.team1241.frc2017.commands.ResetSensorsCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftGearCenterFieldRed extends CommandGroup {

    public LeftGearCenterFieldRed() {
    	// Go to peg
    	addSequential(new DriveCommand(-98, 0.8, 0, 3));
    	addSequential(new TurnCommand(55, 0.8, 2, 1));
    	addSequential(new DriveCommand(-35, 0.6, 57, 2));
    	
    	// Go to center field
    	addSequential(new DriveCommand(50, 0.8, 90, 1.5));
		addSequential(new ResetSensorsCommand());
		addSequential(new DriveCommand(260, 0.8, 90, 3));
    }
}
