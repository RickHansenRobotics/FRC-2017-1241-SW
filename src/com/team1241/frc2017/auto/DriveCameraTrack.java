package com.team1241.frc2017.auto;

import com.team1241.frc2017.Robot;
import com.team1241.frc2017.utilities.Target;

import edu.wpi.first.wpilibj.command.Command;

/** 
 * @author Bryan Kristiono
 * @author Kaveesha Siribaddana
 * @since 13/01/17
 */
public class DriveCameraTrack extends Command {
	private Target target;
	
	private double distance;
	private double speed;
	private double angle;
	private double timeOut;
	private double tolerance;
	private double degree;
	
	private boolean reached = true;

	public DriveCameraTrack(double setPoint, double speed, double timeOut) {
		this(setPoint, speed, timeOut, 1);
	}

    public DriveCameraTrack(double setPoint, double speed, double timeOut, double tolerance) {
    	this.distance = setPoint;
    	this.speed = speed;
    	this.timeOut = timeOut;
    	this.tolerance = tolerance;
    	target = new Target();
    	this.angle  = Robot.drive.getYaw() + Robot.drive.pixelToDegree(target.getCenterX()) - Robot.drive.getOffset(target.getHeight());
    	
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.resetEncoders();
    	setTimeout(timeOut);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	target.updateCoordinates();
    	
    	/*if(Math.abs(Robot.drive.getYaw() - angle) < 15)
    		reached = true;
    	else 
    		reached = false;
    	
    	if(reached)*/
    	degree = Robot.drive.pixelToDegree(target.getCenterX());
    	//if((Math.abs(Robot.drive.getYaw()) - Math.abs(degree)) < 5)
			angle = Robot.drive.getYaw() + degree - Robot.drive.getOffset(target.getHeight());
		//else
			//angle = Robot.drive.getYaw() + degree;
    	Robot.drive.driveSetpoint(distance, speed, angle, tolerance);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut();//Robot.drive.drivePIDDone() || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.runLeftDrive(0);
    	Robot.drive.runRightDrive(0);
    	Robot.drive.resetPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drive.runLeftDrive(0);
		Robot.drive.runRightDrive(0);
		Robot.drive.resetPID();
    }
}
