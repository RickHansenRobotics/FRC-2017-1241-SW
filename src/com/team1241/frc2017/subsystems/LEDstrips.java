package com.team1241.frc2017.subsystems;
import com.team1241.frc2017.ElectricalConstants;
import com.team1241.frc2017.commands.LedCommand;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.PWM.PeriodMultiplier;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LEDstrips extends Subsystem {
	static DigitalOutput hopperLedPWM;

	
	public LEDstrips() {
		hopperLedPWM = new DigitalOutput(ElectricalConstants.HOPPER_PWM_PIN);
	//	shooterLedPWM = new PWM(ElectricalConstants.SHOOTER_PWM_PIN);

		//hopperLedPWM.setPeriodMultiplier(PeriodMultiplier.k1X);	
	//	shooterLedPWM.setPeriodMultiplier(PeriodMultiplier.k1X);	
	}
	
	public static void HopperSetState(boolean state) {
		hopperLedPWM.set(state);
	}
	
//	public void ShooterSetState(int state) {
//		shooterLedPWM.setRaw(state);
//	}

    public void initDefaultCommand() {
    	setDefaultCommand(new LedCommand());
    }
}

