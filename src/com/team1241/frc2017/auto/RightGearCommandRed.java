package com.team1241.frc2017.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightGearCommandRed extends CommandGroup {

	public RightGearCommandRed() {

		addSequential(new DriveCommand(104, 0.8, 0, 3));
		addSequential(new TurnCommand(-57, 0.8, 3, 1));
		addSequential(new DriveCommand(35, 0.6, -57, 6));

	}
}
