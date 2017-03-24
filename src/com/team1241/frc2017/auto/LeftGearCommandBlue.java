package com.team1241.frc2017.auto;

import com.team1241.frc2017.commands.ResetSensorsCommand;
import com.team1241.frc2017.utilities.Point;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftGearCommandBlue extends CommandGroup {

	public LeftGearCommandBlue() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
		addSequential(new DriveCommand(-97, 0.8, 0, 3));
		addSequential(new TurnCommand(57, 0.8, 2, 1));
		addSequential(new DriveCommand(-27, 0.6, 56, 4)); // addSequential(new
															// ResetSensorsCommand());
		// addSequential(new DrivePath(new Point(0,120),new Point(60,240), new
		// Point(60,100), new Point(60,0), 5, 0.8, true));
	}
}
