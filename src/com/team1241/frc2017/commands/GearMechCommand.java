package com.team1241.frc2017.commands;

import com.team1241.frc2017.Robot;
import com.team1241.frc2017.subsystems.LEDstrips;
import com.team1241.frc2017.utilities.ToggleBoolean;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearMechCommand extends Command {

	ToggleBoolean toggle = new ToggleBoolean();

	public GearMechCommand() {
		requires(Robot.gearMech);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		toggle.set(Robot.oi.getToolRightTrigger());


		if(toggle.get() && Robot.gearMech.getBeamBrake()){
			Robot.gearMech.extendGearMech();
		} else {
			Robot.gearMech.retractGearMech();
		} 
		if(Robot.oi.getToolLeftTrigger()){
			Robot.gearMech.extendFeeder();
		}
		else{
			Robot.gearMech.retractFeeder();
		}
		
		if(!Robot.gearMech.getBeamBrake()){
			LEDstrips.gear();
		} else{
			LEDstrips.solid();
		}
		
//		if (Robot.oi.getDriveLeftBumper()) {
//			Robot.gearMech.extendGearMech();
//		} else if (Robot.oi.getToolRightTrigger() && !Robot.gearMech.getOptic()) {
//			Robot.gearMech.retractGearMech();
//		} else if (Robot.oi.getToolRightTrigger()) {
//			Robot.gearMech.extendGearMech();
//		} else if(!Robot.oi.getDriveLeftBumper() && !Robot.oi.getToolRightTrigger()){
//			Robot.gearMech.retractGearMech();
//		} else {
//			Robot.gearMech.retractGearMech();
//		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
