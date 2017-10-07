package com.team1241.frc2017.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightGearCommandBlue extends CommandGroup {

    public RightGearCommandBlue() {
    	addSequential(new DriveCommand(-98, 300, 0, 3,true));
    	addSequential(new TurnCommand(-55, 0.8, 2, 1));
    	addSequential(new DriveCommand(-35, 0.6, -57, 2));
    }
}
