package com.team1241.frc2017.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightGearShootCommandRed extends CommandGroup {

    public RightGearShootCommandRed() {
    	addSequential(new DriveCommand(104, 0.8, 0, 1.6));
    	addSequential(new TurnCommand(-57, 0.8, 0.8, 1));
    	addSequential(new DriveCommand(35, 0.6, -57, 4));
    	addSequential(new DriveCommand(-35, 0.6, -57, 1));
    	addSequential(new TurnCommand(130, 0.8, 1, 1));
    	addSequential(new DriveCommand(101, 0.65, 130, 2));
    }
}
