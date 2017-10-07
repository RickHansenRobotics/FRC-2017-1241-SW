
package com.team1241.frc2017;

import com.team1241.frc2017.auto.CenterGearCommand;
import com.team1241.frc2017.auto.CenterGearShootCommandBlue;
import com.team1241.frc2017.auto.CenterGearShootCommandRed;
import com.team1241.frc2017.auto.DriveCommand;
import com.team1241.frc2017.auto.LeftGearCenterFieldCommandBlue;
import com.team1241.frc2017.auto.LeftGearCenterFieldRed;
import com.team1241.frc2017.auto.LeftGearCommandBlue;
import com.team1241.frc2017.auto.LeftGearCommandRed;
import com.team1241.frc2017.auto.LeftGearShootCommandBlue;
import com.team1241.frc2017.auto.NoAuto;
import com.team1241.frc2017.auto.ProfiledPath;
import com.team1241.frc2017.auto.RightGearCenterFieldBlue;
import com.team1241.frc2017.auto.RightGearCenterFieldCommandRed;
import com.team1241.frc2017.auto.RightGearCommandBlue;
import com.team1241.frc2017.auto.RightGearCommandRed;
import com.team1241.frc2017.auto.RightGearShootCommandRed;
import com.team1241.frc2017.auto.ShootAuton;
import com.team1241.frc2017.auto.TurnCommand;
import com.team1241.frc2017.profiles.DriveStraightProfile;
import com.team1241.frc2017.subsystems.Conveyor;
import com.team1241.frc2017.subsystems.Drivetrain;
import com.team1241.frc2017.subsystems.GearMech;
import com.team1241.frc2017.subsystems.Hanger;
import com.team1241.frc2017.subsystems.LEDstrips;
import com.team1241.frc2017.subsystems.Shooter;
import com.team1241.frc2017.utilities.DataOutput;
import com.team1241.frc2017.utilities.Target;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 
 * @author Team 1241: Theory6
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static Drivetrain drive;

	public static Shooter shooter;
	public static Conveyor conveyor;
	public static Hanger hanger;
	public static GearMech gearMech;
	public static LEDstrips ledstrips;

	Preferences pref;
	public static double rpm;
	public static double conveyorRPM;
	public static double power;
	public static double powerC;
	public static double p;
	public static double i;
	public static double d;
	public static double pDrive;
	public static double iDrive;
	public static double dDrive;
	public static double pGyro;
	public static double iGyro;
	public static double dGyro;

	Command autonomousCommand;
	SendableChooser autoChooser;

	Target target = new Target();

	DataOutput data;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		pref = Preferences.getInstance();
		oi = new OI();
		drive = new Drivetrain();

		shooter = new Shooter();
		hanger = new Hanger();
		gearMech = new GearMech();
		conveyor = new Conveyor();
		ledstrips = new LEDstrips();

		data = new DataOutput("data.txt");

		autoChooser = new SendableChooser();

		autoChooser.addDefault("No Auton", new NoAuto());
		autoChooser.addObject("Profile Path",
				new ProfiledPath(DriveStraightProfile.Points, DriveStraightProfile.kNumPoints));
		autoChooser.addObject("Drive Straight", new DriveCommand(72, 0.6, 0, 5));
		autoChooser.addObject("Turn Command", new TurnCommand(90, 0.8, 5, 1));
		autoChooser.addObject("Left Gear Command Red", new LeftGearCommandRed());
		autoChooser.addObject("Left Gear Command Blue", new LeftGearCommandBlue());
		autoChooser.addObject("Right Gear Command Red", new RightGearCommandRed());
		autoChooser.addObject("Center Gear Command", new CenterGearCommand());
		autoChooser.addObject("Shoot Auto", new ShootAuton());
		autoChooser.addObject("Left Gear Shoot Command Blue", new LeftGearShootCommandBlue());
		autoChooser.addObject("Left Gear Center Field Command Blue", new LeftGearCenterFieldCommandBlue());
		autoChooser.addObject("Right Gear Shoot Command Red", new RightGearShootCommandRed());
		autoChooser.addObject("Right Gear Center Field Command Red", new RightGearCenterFieldCommandRed());
		autoChooser.addObject("Center Gear & Shoot RED", new CenterGearShootCommandRed());
		autoChooser.addObject("Center Gear & Shoot BLUE", new CenterGearShootCommandBlue());
		autoChooser.addObject("Right Gear Command Blue", new RightGearCommandBlue());
		autoChooser.addObject("Right Gear Center Field Blue", new RightGearCenterFieldBlue());
		autoChooser.addObject("Left Gear Center Field Red", new LeftGearCenterFieldRed());
		

		SmartDashboard.putData("Auto Modes", autoChooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {
		LEDstrips.disabled();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
		//LEDstrips.HopperSetState(LedConstants.HOPPER_BALL);

	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		 autonomousCommand = (Command) autoChooser.getSelected();
		 LEDstrips.gold();
	
		drive.resetEncoders();
		drive.resetGyro();

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		data.writeData(drive.getYaw(), drive.getLeftDriveEncoder(), drive.getRightDriveEncoder(), 0);

		// updateSmartDashboard();
	}

	public void teleopInit() {
		rpm = pref.getDouble("RPM", 0.0);
		conveyorRPM = pref.getDouble("Conveyor RPM", 0.0);
		power = pref.getDouble("Shooter Power", 0.0);
		powerC = pref.getDouble("Conveyor Power", 0.0);
		p = pref.getDouble("Shooter pGain", 0.0);
		drive.resetGyro();
		
		LEDstrips.solidGreen();
		//LEDstrips.gear();


		data.close();
		// if(Robot.oi.getToolBackButton())
		// dataOutput.writeData(counter, drive.getLeftDriveEncoder(),
		// drive.getRightDriveEncoder(), drive.getYaw());
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		/*
		 * try { new UDPClient().start(); SmartDashboard.putString("thread",
		 * "start"); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); SmartDashboard.putString("thread",
		 * e.toString()); }
		 */
		pref = Preferences.getInstance();
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
		// data.writeStatement(""+target.getCenterX(), ""+drive.getYaw());

		/*
		 * if(oi.getToolBackButton()){ //new BaseCameraTrack().start(); //new
		 * TurnCommand(-15, 0.8, 2, 1).start(); new DriveCameraTrack(36, 0.4,
		 * 3).start(); } if(oi.getToolRightAnalogButton()) data.close();
		 */
		// Robot.intake.retractIntake();
		// hopper.retractHopper();

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

	int counter = 0;

	public void updateSmartDashboard() {
		rpm = pref.getDouble("RPM", 0.0);
		power = pref.getDouble("Shooter Power", 0.0);
		powerC = pref.getDouble("Conveyor Power", 0.0);
		p = pref.getDouble("Shooter pGain", 0.0);
		i = pref.getDouble("Shooter iGain", 0.0);
		d = pref.getDouble("Shooter dGain", 0.0);

		pDrive = pref.getDouble("Drive pGain", 0.0);
		iDrive = pref.getDouble("Drive iGain", 0.0);
		dDrive = pref.getDouble("Drive dGain", 0.0);

		pGyro = pref.getDouble("Gyro pGain", 0.0);
		iGyro = pref.getDouble("Gyro iGain", 0.0);
		dGyro = pref.getDouble("Gyro dGain", 0.0);

		counter++;
		if (counter % 50 == 0) {
			SmartDashboard.putNumber("counter", counter / 50);
		}
		SmartDashboard.putBoolean("Can Shoot", shooter.shooterPID.isDone());
		SmartDashboard.putNumber("Shooter RPM", Math.round(shooter.getRPM(rpm)));
		SmartDashboard.putNumber("Set RPM", rpm);
		SmartDashboard.putNumber("Set Power", power);
		SmartDashboard.putNumber("Gyro Angle", drive.getYaw());
		SmartDashboard.putNumber("Right Encoder", drive.getRightDriveEncoder());
		SmartDashboard.putNumber("Left Encoder", drive.getLeftDriveEncoder());
		SmartDashboard.putBoolean("Limit Switch", hanger.limitEngaged());
		SmartDashboard.putNumber("Left Speed", drive.getLeftSpeed());
		SmartDashboard.putNumber("Right Speed", drive.getRightSpeed());
		SmartDashboard.putNumber("Conveyor Speed", conveyor.getConveyorSpeed());

		//SmartDashboard.putBoolean("Optical", !gearMech.getOptic());

		SmartDashboard.putBoolean("Optical", !gearMech.getBeamBrake());

		SmartDashboard.putString("Selected Auto", autoChooser.getSelected().toString());

	
		SmartDashboard.putBoolean("MechState", gearMech.getBeamBrake());
		SmartDashboard.putBoolean("Claw State", gearMech.getMechState());		
		//Beam Brake
		SmartDashboard.putBoolean("BeamBrake", gearMech.getBeamBrake());

	}
}
