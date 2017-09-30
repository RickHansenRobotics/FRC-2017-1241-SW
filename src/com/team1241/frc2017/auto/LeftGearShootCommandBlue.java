package com.team1241.frc2017.auto;

import com.team1241.frc2017.commands.AutoShootSequence;
import com.team1241.frc2017.commands.SetRPM;
import com.team1241.frc2017.profiles.SidePegProfile;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftGearShootCommandBlue extends CommandGroup {

	public LeftGearShootCommandBlue() {

		addSequential(new DriveCommand(-94, 0.8, 0, 2.25));
		//addSequential(new ProfiledPath(SidePegProfile.Points, SidePegProfile.kNumPoints));
		addSequential(new TurnCommand(57, 0.8, 1.25, 1));
		addSequential(new DriveCommand(-34, 0.6, 56, 3));

		addSequential(new DriveCommand(119, 0.8, 42, 1.75));
		addParallel(new SetRPM(2775));
		addSequential(new AutoShootSequence());

	}

}
