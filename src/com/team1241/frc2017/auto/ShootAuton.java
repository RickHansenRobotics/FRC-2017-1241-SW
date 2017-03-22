package com.team1241.frc2017.auto;

import com.team1241.frc2017.commands.AutoShootSequence;
import com.team1241.frc2017.commands.SetRPM;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootAuton extends CommandGroup {

    public ShootAuton() {
    	addSequential(new IntakePistonCommand(true));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new HopperPistonCommand(true));
        addParallel(new SetRPM(2825, 10));
        addSequential(new AutoShootSequence());
        
    }
}
