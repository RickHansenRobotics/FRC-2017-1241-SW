package com.team1241.frc2017.auto;

import com.team1241.frc2017.commands.ResetSensorsCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightGearCenterFieldBlue extends CommandGroup {

    public RightGearCenterFieldBlue() {
    	addSequential(new DriveCommand(-98, 300, 0, 3,true));
    	addSequential(new TurnCommand(-55, 0.8, 2, 1));
    	addSequential(new DriveCommand(-35, 0.6, -57, 2));
    	
    	addSequential(new DriveCommand(40, 0.8, -90, 1.5));
		addSequential(new ResetSensorsCommand());
		addSequential(new DriveCommand(270, 0.8, -90, 3.5));
    	
    	
    }
}
