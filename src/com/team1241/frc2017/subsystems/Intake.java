package com.team1241.frc2017.subsystems;

import com.team1241.frc2017.ElectricalConstants;
import com.team1241.frc2017.commands.IntakeCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Kaveesha Siribaddana
 * @since 14/01/17
 */
public class Intake extends Subsystem {

	Talon mainIntakeRollers;
	Spark sideIntakeRollers;

	DoubleSolenoid intakePiston;

	PowerDistributionPanel pdp;

	public Intake() {

		// Initialize Sparks
		mainIntakeRollers = new Talon(ElectricalConstants.MAIN_INTAKE_ROLLERS);
		sideIntakeRollers = new Spark(ElectricalConstants.SIDE_INTAKE_ROLLERS);

		intakePiston = new DoubleSolenoid(ElectricalConstants.INTAKE_PISTON_A,
										  ElectricalConstants.INTAKE_PISTON_B);

	}

	public void setIntakeSpeed(double speed) {
		mainIntakeRollers.set(speed);
		sideIntakeRollers.set(speed);
	}

	// INTAKE PISTON COMMANDS

	public void extendIntake() {
		intakePiston.set(DoubleSolenoid.Value.kForward);
	}

	public void retractIntake() {
		intakePiston.set(DoubleSolenoid.Value.kReverse);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new IntakeCommand());
	}

//	public double getLeftMotorDraw() {
//		return pdp.getCurrent(ElectricalConstants.AGITATOR_MOTOR);
//	}
//
//	public double getRightMotorDraw() {
//		return pdp.getCurrent(ElectricalConstants.CONVEYOR_MOTOR);
//	}
}
