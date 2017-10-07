package com.team1241.frc2017.auto;

import com.team1241.frc2017.commands.ResetSensorsCommand;
import com.team1241.frc2017.utilities.Point;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftGearCommandBlue extends CommandGroup {

	public LeftGearCommandBlue() {
		addSequential(new DriveCommand(-94, 0.8, 0, 2.25));
		addSequential(new TurnCommand(57, 0.8, 1.25, 1));
		addSequential(new DriveCommand(-34, 0.6, 56, 3));
	}
}
