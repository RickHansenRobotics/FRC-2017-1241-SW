package com.team1241.frc2017.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightGearCommandRed extends CommandGroup {

	public RightGearCommandRed() {

		addSequential(new DriveCommand(-91, 300, 0, 3,true));
		addSequential(new TurnCommand(-57, 0.8, 1.25, 1));
		addSequential(new DriveCommand(-36, 0.6, -58, 3));

	}
}
