package com.team1241.frc2017.auto;

import com.team1241.frc2017.commands.AutoConveyorCommand;
import com.team1241.frc2017.commands.AutoShootSequence;
import com.team1241.frc2017.commands.SetRPM;
import com.team1241.frc2017.utilities.Point;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class HopperAutoBlue extends CommandGroup {

    public HopperAutoBlue() {
    	//addParallel(new AutoOpenSequence());
    	//addSequential(new DrivePath(new Point(0,0), new Point(-26,34), new Point(0,60), new Point(-60,60), 5, 0.8));
    	addSequential(new DriveCommand(-115, 1, 0, 2.5));
    	addSequential(new DriveCommand(-34, 1, 25, 3));
    	addSequential(new AutoConveyorCommand(1, -1));
    	addSequential(new DriveCommand(78, 0.5, 25, 3));
    	addParallel(new SetRPM(2775));
    	addParallel(new AutoShootSequence());
    	addSequential(new DriveCommand(30, 0.8, 90, 1.5));
    }
}
