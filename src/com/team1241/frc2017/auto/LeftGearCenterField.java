package com.team1241.frc2017.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftGearCenterField extends CommandGroup {

    public LeftGearCenterField() {
    	addSequential(new DriveCommand(-96, 0.8, 0, 2.25));
		//addSequential(new ProfiledPath(SidePegProfile.Points, SidePegProfile.kNumPoints));
		addSequential(new TurnCommand(57, 0.8, 1.25, 1));
		addSequential(new DriveCommand(-29, 0.6, 56, 3));
    }
}
