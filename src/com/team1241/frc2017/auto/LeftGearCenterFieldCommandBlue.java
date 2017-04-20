package com.team1241.frc2017.auto;

import com.team1241.frc2017.commands.ResetSensorsCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftGearCenterFieldCommandBlue extends CommandGroup {

    public LeftGearCenterFieldCommandBlue() {
    	//Score Gear
    	addSequential(new DriveCommand(-94, 0.8, 0, 2.25));
		//addSequential(new ProfiledPath(SidePegProfile.Points, SidePegProfile.kNumPoints));
		addSequential(new TurnCommand(57, 0.8, 1.25, 1));
		addSequential(new DriveCommand(-34, 0.6, 56, 3));
		
		//Drive to center of field
		addSequential(new DriveCommand(50, 0.8, 90, 1.5));
		addSequential(new ResetSensorsCommand());
		addSequential(new DriveCommand(270, 0.8, 125, 3));
    }
}
