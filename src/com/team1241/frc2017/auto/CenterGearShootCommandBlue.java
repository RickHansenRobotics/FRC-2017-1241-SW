package com.team1241.frc2017.auto;

import com.team1241.frc2017.commands.AutoShootSequence;
import com.team1241.frc2017.commands.SetRPM;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterGearShootCommandBlue extends CommandGroup {

    public CenterGearShootCommandBlue() {
    	addSequential(new DriveCommand(-67, 0.8, 0, 2));
    	addSequential(new WaitCommand(1));
    	addSequential(new DriveCommand(-24, 1, 0, 2));
    	addSequential(new ContinousMotion(0.5, 0, 3, 0.75));
    	addParallel(new SetRPM(2775));
    	addSequential(new DriveCommand(140, 1, 62, 2));
    	addSequential(new AutoShootSequence());
    }
}
