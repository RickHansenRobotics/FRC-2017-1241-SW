package com.team1241.frc2017;

/**
 *        The ElectricalConstants is a mapping from the ports sensors and
 *        actuators are wired into to a variable name. This provides flexibility
 *        changing wiring, makes checking the wiring easier and significantly
 *        reduces the number of magic numbers floating around.
 *        
 *@author Kaveesha Siribaddana
 *@since 11/01/17
 */

public class ElectricalConstants {

	// ***************************************************************************
	// *********************** DRIVE MOTORS [TALONS] *****************************
	// ***************************************************************************

	public static final int LEFT_DRIVE_FRONT 								= 1;
	public static final int LEFT_DRIVE_BACK 								= 2;

	public static final int RIGHT_DRIVE_FRONT 								= 9;
	public static final int RIGHT_DRIVE_BACK 								= 10;
	
	// ***************************************************************************
	// ************************* INTAKE MOTORS [SPARKS] **************************
	// ***************************************************************************
	
	public static final int MAIN_INTAKE_ROLLERS								= 0;
	public static final int SIDE_INTAKE_ROLLERS                             = 1;
	
	// ***************************************************************************
	// ********************* SHOOTER MOTORS [TALON SRX]***************************
	// ***************************************************************************
	
	public static final int LEFT_SHOOTER_MOTOR								= 4;
	public static final int RIGHT_SHOOTER_MOTOR								= 5;
	
	// ***************************************************************************
	// ************************* HANG MOTORS [VICTORS] ***************************
	// ***************************************************************************
	public static final int LEFT_HANG_MOTOR									= 2;
	public static final int RIGHT_HANG_MOTOR								= 3;
	
	public static final int HANGER_LIMIT_SWITCH                             = 0;
	
	// ***************************************************************************
	// **************************** CONVEYOR MOTORS ******************************
	// ***************************************************************************
	
	public static final int AGITATOR_MOTOR 									= 3;
	public static final int AGITATOR_HOPPER 								= 6;
	
	public static final int CONVEYOR_MOTOR1									= 8;
	public static final int CONVEYOR_MOTOR2									= 7;
	
	
	// ***************************************************************************
	// ******************************* PISTONS ***********************************
	// ***************************************************************************
	
    public static final int GEAR_PISTON_A 									= 6;
    public static final int GEAR_PISTON_B  									= 1;
    
    public static final int FEEDER_PISTON_A                                 = 3; //0
    public static final int FEEDER_PISTON_B 								= 4; //7
    
    public static final int STABILIZER_PISTON_A								= 2;
    public static final int STABILIZER_PISTON_B	  							= 5;
    
    // ***************************************************************************
 	// *********************** OPTICAL SENSOR [DIGITAL] **************************
 	// ***************************************************************************
 	
 	public static final int OPTICAL_SENSOR_SHOOTER                          = 1;
<<<<<<< HEAD
 	public static final int OPTICAL_SENSOR_GEARMECH                         = 2;
=======
>>>>>>> refs/remotes/origin/master
 	
 	// ***************************************************************************
  	// *********************** BEAM BRAKE [DIGITAL] **************************
  	// ***************************************************************************
 	
 	public static final int BEAM_BRAKE_GEARMECH							    = 4;
    
    //****************************************************************************
  	//************************ CONVEYOR ENCODER CONSTANTS ************************
  	//****************************************************************************
  	public static final int conveyorPullyRadius = 4;//wheel radius in inches
  	public static final int conveyorPulsePerRotation = 128; //encoder pulse per rotation
  	public static final double conveyorGearRatio = 1/1; //ratio between wheel and encoder
  	public static final double conveyorEncoderPulsePerRot = conveyorPulsePerRotation*conveyorGearRatio; //pulse per rotation * gear ratio
  	public static final double conveyorEncoderDistPerTick =(Math.PI*2*conveyorPullyRadius)/conveyorEncoderPulsePerRot;
  	public static final boolean conveyorEncoderReverse = false;
  	
    //**************************************************************************
    //************************* DRIVE ENCODER CONSTANTS ************************
    //**************************************************************************
	private static final double encoderGearRatio 					     = 42/36;
	private static final double wheelRadius								 = 2;
	public static final double ROTATIONS_TO_INCHES 		= 14.46;//2*Math.PI*wheelRadius*encoderGearRatio;
	
	//**************************************************************************
    //**************************** LED PWM CONSTANTS ***************************
    //**************************************************************************
	//public static final int HOPPER_PWM_PIN 							= 9;
	//public static final int SHOOTER_PWM_PIN							= 9;
	
	public static final int BYTE_ONE_PIN 							= 8;	// connect this to third from top
	public static final int BYTE_TWO_PIN							= 9;	// connect this to last from top
	

			
	
}