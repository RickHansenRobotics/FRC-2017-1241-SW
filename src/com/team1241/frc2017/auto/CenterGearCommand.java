package com.team1241.frc2017.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterGearCommand extends CommandGroup {

    public CenterGearCommand() {
    	addSequential(new DriveCommand(95.5, 0.4, 0, 5));
    }
}
