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

    DoubleSolenoid gearMechPiston;
    DoubleSolenoid feederPiston;
    
    private boolean contains = false;
	private boolean mechState = false;
	
	 private boolean feederState = false;
    
    private DigitalInput beamBrake;
<<<<<<< HEAD
    private DigitalInput optical;
=======
>>>>>>> refs/remotes/origin/master
    
    public GearMech(){
    	gearMechPiston = new DoubleSolenoid(ElectricalConstants.GEAR_PISTON_A,ElectricalConstants.GEAR_PISTON_B);
    	feederPiston = new DoubleSolenoid(ElectricalConstants.FEEDER_PISTON_A,ElectricalConstants.FEEDER_PISTON_B);

    	beamBrake = new DigitalInput(ElectricalConstants.BEAM_BRAKE_GEARMECH);
<<<<<<< HEAD
    	optical = new DigitalInput(ElectricalConstants.OPTICAL_SENSOR_GEARMECH);
=======
>>>>>>> refs/remotes/origin/master
    }
    
    public void extendGearMech(){
    	gearMechPiston.set(DoubleSolenoid.Value.kForward);
    	mechState = true;
    }
    
    public void retractGearMech(){
    	gearMechPiston.set(DoubleSolenoid.Value.kReverse);
    	mechState = false;
    }
    public void extendFeeder(){
    	feederPiston.set(DoubleSolenoid.Value.kForward);
    	feederState = true;
    }
    
    public void retractFeeder(){
    	feederPiston.set(DoubleSolenoid.Value.kReverse);
    	feederState = false;
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
    
    public boolean getBeamBrake(){
    	return beamBrake.get();
    }

    public void initDefaultCommand() {
        setDefaultCommand(new GearMechCommand());
    }
}