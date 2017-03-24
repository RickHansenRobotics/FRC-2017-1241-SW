package com.team1241.frc2017.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightGearCommandRed extends CommandGroup {

	public RightGearCommandRed() {

		addSequential(new DriveCommand(-92, 0.6, 0, 3));
		addSequential(new TurnCommand(-11, 1, 3, 1));
		addSequential(new DriveCommand(-34, 0.6, -57, 6));

	}
}
