package com.team1241.frc2017.commands;

import com.team1241.frc2017.auto.GearMechPistonCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class GearMechSequence extends CommandGroup {

    public GearMechSequence() {
        addSequential(new WaitCommand(0.5));
        addSequential(new GearMechPistonCommand(false));
    }
}
