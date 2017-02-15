package com.team1241.frc2017.commands;

import com.team1241.frc2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ConveyorCommand extends Command {

	public ConveyorCommand() {
		requires(Robot.conveyor);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

//		if (Robot.oi.getToolAButton()) {
//			Robot.conveyor.conveyorMotor(Robot.powerC);
//			Robot.conveyor.agitatorMotor(Robot.powerC);
//		} else if (Robot.oi.getToolXButton()) {
//			Robot.conveyor.conveyorMotor(-Robot.powerC);
//			Robot.conveyor.agitatorMotor(-Robot.powerC);
//		} else if (Robot.oi.getToolBButton()) {
//			Robot.conveyor.conveyorMotor(Robot.powerC);
//			Robot.conveyor.agitatorMotor(Robot.powerC);
//		} else if (Robot.oi.getToolYButton()) {
//			Robot.conveyor.conveyorMotor(-Robot.powerC);
//			Robot.conveyor.agitatorMotor(-Robot.powerC);
//		} else {
//			Robot.conveyor.conveyorMotor(0);
//			Robot.conveyor.agitatorMotor(0);
//		}
		
		if(Robot.oi.getToolXButton()){
			Robot.hanger.hangMotor(0.8);
		} else if(Robot.oi.getToolAButton()){
			Robot.hanger.hangMotor(-0.8);
		} else{
			Robot.hanger.hangMotor(0);
		}
		
		if(Robot.oi.getToolAButton()){
			Robot.conveyor.agitatorMotor(-0.7);
		} else if (Robot.oi.getToolXButton()){
			Robot.conveyor.agitatorMotor(0.7);
		} else {
			Robot.conveyor.agitatorMotor(0);
		}

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
