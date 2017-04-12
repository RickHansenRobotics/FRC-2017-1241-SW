package com.team1241.frc2017.auto;

import com.team1241.frc2017.commands.ResetSensorsCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightGearCenterFieldCommandRed extends CommandGroup {

    public RightGearCenterFieldCommandRed() {
    	addSequential(new DriveCommand(-94, 0.8, 0, 2.25));
		addSequential(new TurnCommand(-57, 0.8, 1.25, 1));
		addSequential(new DriveCommand(-29, 0.6, -56, 3));
		
		addSequential(new DriveCommand(35, 0.8, -90, 1.5));
		addSequential(new ResetSensorsCommand());
		addSequential(new DriveCommand(150, 0.8, -135, 3));
    }
}
