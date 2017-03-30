package com.team1241.frc2017.auto;

import com.team1241.frc2017.Robot;
import com.team1241.frc2017.utilities.MotionProfile;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Mahrus Kazi
 * @since 01-17-2017
 */
public class ProfiledPath extends Command {
	
	private double[][] pointsRight;
	private double[][] pointsLeft;
	private int size;

	private MotionProfile profileRight;
	private MotionProfile profileLeft;
	
    public ProfiledPath(double[][] points, int size) {
        requires(Robot.drive);
        this.pointsRight = points;
        this.pointsLeft = points;
        this.size = size;
    }
    
    public ProfiledPath(double[][] pointsRight, int sizeRight, double[][] pointsLeft, int sizeLeft) {
        requires(Robot.drive);
        this.pointsRight = pointsRight;
        this.pointsLeft = pointsLeft;
        this.size = sizeRight;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	profileRight = new MotionProfile(Robot.drive.getRightMaster(), pointsRight, size);
        profileLeft = new MotionProfile(Robot.drive.getLeftMaster(), pointsLeft, size);
        
    	profileRight.reset();
    	profileLeft.reset();
    	Robot.drive.resetEncoders();
    	Robot.drive.motionProfileMode();
    	
    	profileRight.startMotionProfile();
    	profileLeft.startMotionProfile();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	profileRight.control();
    	profileLeft.control();
    	Robot.drive.runRightDrive(profileRight.getSetValue().value);
    	Robot.drive.runLeftDrive(profileLeft.getSetValue().value);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return profileRight.getSetValue().value == 2 && profileLeft.getSetValue().value == 2;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.voltageMode();
    	Robot.drive.runRightDrive(0);
    	Robot.drive.runLeftDrive(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drive.voltageMode();
    	Robot.drive.runRightDrive(0);
    	Robot.drive.runLeftDrive(0);
    }
}
