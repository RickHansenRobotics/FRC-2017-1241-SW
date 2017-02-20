package com.team1241.frc2017.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftGearCommandRed extends CommandGroup {

    public LeftGearCommandRed() {
    	addSequential(new DriveCommand(107, 0.8, 0, 5));
    	addSequential(new TurnCommand(61, 0.8, 3, 1));
    	addSequential(new DriveCommand(35, 0.6, 61, 5));
    }
} 
