package com.team1241.frc2017.auto;

import com.team1241.frc2017.commands.AutoShootSequence;
import com.team1241.frc2017.commands.SetRPM;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftGearShootCommandBlue extends CommandGroup {

	public LeftGearShootCommandBlue() {

		addSequential(new DriveCommand(-94, 0.8, 0, 2.25));
		addSequential(new TurnCommand(57, 0.8, 1.25, 1));
		addSequential(new DriveCommand(-34, 0.5, 56, 3));

		addParallel(new AutoOpenSequence());
		addSequential(new DriveCommand(119, 0.8, 45, 1.75));
		addParallel(new SetRPM(2750));
		addSequential(new AutoShootSequence());

	}

}
