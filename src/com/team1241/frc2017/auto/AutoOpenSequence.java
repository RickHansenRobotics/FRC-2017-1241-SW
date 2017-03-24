package com.team1241.frc2017.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoOpenSequence extends CommandGroup {

    public AutoOpenSequence() {
    	addSequential(new IntakePistonCommand(true));
    	addSequential(new WaitCommand(0.15));
    	addSequential(new HopperPistonCommand(true));
    }
}
