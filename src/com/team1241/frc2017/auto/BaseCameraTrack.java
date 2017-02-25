package com.team1241.frc2017.auto;

import com.team1241.frc2017.Robot;
import com.team1241.frc2017.utilities.Target;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BaseCameraTrack extends Command {

	private Target target;
	private TurnCommand turn;

	private double timeout;
	private double degree;
	
	private double prevXVal;
	private double xVal;
	private boolean hasChanged;
	private boolean started;
	private boolean reached = false;
	
	public BaseCameraTrack(){
		this(2.0);
	}

	public BaseCameraTrack(double timeout) {
		this.timeout = timeout;
		target = new Target();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		hasChanged = true;
		started = false;
		prevXVal = 0;
		if(timeout > 0)
			setTimeout(timeout);
		
		turn = new TurnCommand(0, 0.8, 2, 1);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		target.updateCoordinates();
		xVal = target.getCenterX();
		
		if(target.numOfTargets() == 2){
			
			if (prevXVal != xVal)
				hasChanged = true;
			
			if (hasChanged && !started) {
				turn.cancel();

				degree = Robot.drive.pixelToDegree(xVal);
				
				//if(Math.abs(degree) < 5 || reached){
					//reached = true;
					turn.changeAngle(Robot.drive.getYaw() + degree - Robot.drive.getOffset(target.getHeight()));
				//}
				//else if(!reached)
					//turn.changeAngle(Robot.drive.getYaw() + degree);
				turn.start();
				hasChanged = false;
			}
			if (turn.isFinished())
				started = false;
			else
				started = true;
			
			prevXVal = xVal;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (timeout < 0) {
			return false;
		} else {
			return isTimedOut();
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drive.runLeftDrive(0);
		Robot.drive.runRightDrive(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.drive.runLeftDrive(0);
		Robot.drive.runRightDrive(0);
	}
}
