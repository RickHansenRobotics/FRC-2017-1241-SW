package com.team1241.frc2017.auto;

import com.team1241.frc2017.commands.ResetSensorsCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightGearCenterFieldCommandRed extends CommandGroup {

    public RightGearCenterFieldCommandRed() {
    	addSequential(new DriveCommand(-86.5, 300, 0, 3,true));
		addSequential(new TurnCommand(-56, 0.8, 1.25, 1));
		addSequential(new DriveCommand(-36, 0.6, -57, 3));
		
		addSequential(new DriveCommand(50, 0.8, -90, 1.5));
		addSequential(new ResetSensorsCommand());
		addSequential(new DriveCommand(270, 0.8, -125, 3));
    }
}
