package com.team1241.frc2017.commands;

import com.team1241.frc2017.auto.HangerPistonCommand;
import com.team1241.frc2017.auto.WaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class HangSequence extends CommandGroup {

	public HangSequence() {
		addSequential(new WaitCommand(0.125));
		addSequential(new WaitCommand(0.125));
		addSequential(new HangerPistonCommand(false));
	}
}
