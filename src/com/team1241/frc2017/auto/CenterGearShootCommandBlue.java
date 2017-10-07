package com.team1241.frc2017.auto;

import com.team1241.frc2017.commands.AutoShootSequence;
import com.team1241.frc2017.commands.SetRPM;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterGearShootCommandBlue extends CommandGroup {

    public CenterGearShootCommandBlue() {
    	// Drives forward to peg
    	addSequential(new DriveCommand(-67, 0.8, 0, 2));
    	addSequential(new WaitCommand(1));
    	addSequential(new DriveCommand(-24, 1, 0, 2));
    	
    	// Wiggle Wiggle Wiggle
    	addSequential(new ContinousMotion(0.5, 0, 3, 0.75));
<<<<<<< HEAD
    	
    	// Ready to shoot
    	addSequential(new AutoOpenSequence());
=======
>>>>>>> refs/remotes/origin/master
    	addParallel(new SetRPM(2775));
    	addSequential(new DriveCommand(140, 1, 64, 2));
    	addSequential(new AutoShootSequence());
    }
}
