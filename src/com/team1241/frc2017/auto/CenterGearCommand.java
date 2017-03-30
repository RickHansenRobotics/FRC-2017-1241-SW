package com.team1241.frc2017.auto;

import com.team1241.frc2017.commands.DriveLeftSide;
import com.team1241.frc2017.commands.DriveRightSide;
import com.team1241.frc2017.profiles.DriveStraightProfile;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterGearCommand extends CommandGroup {

    public CenterGearCommand() {
    	addSequential(new DriveCommand(-63, 210, 0, 3, true));
    	//addSequential(new ProfiledPath(DriveStraightProfile.Points, DriveStraightProfile.kNumPoints));
    	addSequential(new WaitCommand(2));
    	addSequential(new DriveCommand(-31, 200, 0, 3, true));
//    	//addSequential(new DriveCommand(-6, 0.9, 0, 3));
//    	addSequential(new ContinousMotion(0.15, 20, 3));
    	
    	/*addSequential(new DriveRightSide(1.5));
    	addSequential(new DriveLeftSide(1.5));
    	
    	addSequential(new DriveRightSide(1.5));
    	addSequential(new DriveLeftSide(1.5));
    	
    	addSequential(new DriveRightSide(1.5));
    	addSequential(new DriveLeftSide(1.5));
    	
    	addSequential(new DriveRightSide(1.5));
    	addSequential(new DriveLeftSide(1.5));*/
    }
}
