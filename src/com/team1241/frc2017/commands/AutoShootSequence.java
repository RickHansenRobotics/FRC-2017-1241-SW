package com.team1241.frc2017.commands;

import com.team1241.frc2017.auto.DriveCommand;
import com.team1241.frc2017.auto.WaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoShootSequence extends CommandGroup {

    public AutoShootSequence() {
    	
    	addSequential(new WaitCommand(0.75));
    	addSequential(new AutoConveyorCommand(2,3));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new WaitCommand(3.5));
    }
}
