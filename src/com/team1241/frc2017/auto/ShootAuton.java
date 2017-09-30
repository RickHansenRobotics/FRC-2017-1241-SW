package com.team1241.frc2017.auto;

import com.team1241.frc2017.commands.AutoConveyorCommand;
import com.team1241.frc2017.commands.AutoShootSequence;
import com.team1241.frc2017.commands.SetRPM;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootAuton extends CommandGroup {

    public ShootAuton() {
    	addSequential(new WaitCommand(0.5));
        addParallel(new SetRPM(2825, 10));
        addSequential(new AutoShootSequence());
        addSequential(new AutoConveyorCommand(0,0.1));
    	//BLUE
    	//addSequential(new DriveCommand(-110, 0.8, -60, 3));
    	addSequential(new DriveCommand(-110, 300, -60, 3));
    	//RED
    	//addSequential(new DriveCommand(-110, 0.8, 60, 3));
    	//addSequential(new DriveCommand(-110, 300, 60, 3));
        
    }
}
