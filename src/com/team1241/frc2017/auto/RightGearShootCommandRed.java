package com.team1241.frc2017.auto;

import com.team1241.frc2017.commands.AutoShootSequence;
import com.team1241.frc2017.commands.SetRPM;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightGearShootCommandRed extends CommandGroup {

    public RightGearShootCommandRed() {
    	addSequential(new DriveCommand(-91, 300, 0, 3,true));
		addSequential(new TurnCommand(-57, 0.8, 1.25, 1));
		addSequential(new DriveCommand(-36, 0.6, -58, 3));

		addParallel(new AutoOpenSequence());
		addSequential(new DriveCommand(119, 0.8, -40, 1.75));
		addParallel(new SetRPM(2775));
		addSequential(new AutoShootSequence());
    }
}
