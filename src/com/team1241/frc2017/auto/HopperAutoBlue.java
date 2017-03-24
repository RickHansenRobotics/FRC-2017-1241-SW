package com.team1241.frc2017.auto;

import com.team1241.frc2017.utilities.Point;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class HopperAutoBlue extends CommandGroup {

    public HopperAutoBlue() {
    	//addParallel(new AutoOpenSequence());
    	addSequential(new DrivePath(new Point(0,0), new Point(-26,34), new Point(0,60), new Point(-60,60), 5, 0.8));
    }
}
