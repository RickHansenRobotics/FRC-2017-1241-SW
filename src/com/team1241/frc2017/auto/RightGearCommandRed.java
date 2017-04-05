package com.team1241.frc2017.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightGearCommandRed extends CommandGroup {

	public RightGearCommandRed() {

		addSequential(new DriveCommand(-97, 0.8, 0, 2.25));
		addSequential(new TurnCommand(-57, 0.8, 1.25, 1));
		addSequential(new DriveCommand(-27, 0.6, -56, 2));

	}
}
