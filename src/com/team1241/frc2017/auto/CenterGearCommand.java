package com.team1241.frc2017.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterGearCommand extends CommandGroup {

    public CenterGearCommand() {
    	addSequential(new DriveCommand(-67, 0.8, 0, 2));
    	addSequential(new WaitCommand(1));
    	addSequential(new DriveCommand(-24, 1, 0, 2));
    	

    }
}
