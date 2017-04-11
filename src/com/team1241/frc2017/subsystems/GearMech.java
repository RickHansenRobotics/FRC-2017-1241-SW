package com.team1241.frc2017.subsystems;

import com.team1241.frc2017.ElectricalConstants;
import com.team1241.frc2017.commands.GearMechCommand;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearMech extends Subsystem {

    DoubleSolenoid gearPiston;
    
    private boolean contains = false;
	private boolean mechState = false;
    
    private DigitalInput optical;
    
    public GearMech(){
    	gearPiston = new DoubleSolenoid(ElectricalConstants.GEAR_PISTON_A,ElectricalConstants.GEAR_PISTON_B);
    	
    	optical = new DigitalInput(ElectricalConstants.OPTICAL_SENSOR_GEARMECH);
    }
    
    public void extendGearMech(){
    	gearPiston.set(DoubleSolenoid.Value.kForward);
    	mechState = true;
    }
    
    public void retractGearMech(){
    	gearPiston.set(DoubleSolenoid.Value.kReverse);
    	mechState = false;
    }
    
    public void setContains(boolean state) {
		this.contains = state;
	}
	
	public boolean getContains() {
		return contains;
	}
	
	public boolean getMechState(){
		return mechState;
	}
    
    public boolean getOptic(){
    	return optical.get();
    }

    public void initDefaultCommand() {
        setDefaultCommand(new GearMechCommand());
    }
}

