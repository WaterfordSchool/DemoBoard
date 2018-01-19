package org.usfirst.frc.team3166.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	/**
	 * These are parentheses ()
	 * These are brackets []
	 * These are braces {}
	 * 
	 * When you read this code and get to an equal sign =, say to yourself "is
	 * assigned the value of"; a = b is read as "a is assigned the value of b"
	 * If you want to check if a and b have the same value use ==
	 */

	/**
	 * Naming conventions from the online Oracle Java Tutuorials
	 * 
	 * Variable names are case sensitive. If the name you choose consists of only one word, spell
	 * that word in all lowercase letters. If it consists of more than one word, capitalize the
	 * first letter of each subsequent word. If your variable stores a constant value, capitalize
	 * every letter and separate subsequent words with underscore.
	 * 
	 * Method names should be a verb in lowercase or a multi-word name that begins with a verb in
	 * lowercase followed by adjectives, nouns, etc. In multi-word names, the first letter of each
	 * subsequent word should be capitalized.
	 * 
	 * Class names-the first letter of a class name should be capitalized.
	 */

	/**
	 * Expressions, Statements, and Blocks
	 * 
	 * Expression-a construct of variables, operators and method invocations, which are constructed
	 * according to the syntax of the language, that evaluates to a single value.
	 * Statements-a statement forms a complete unit of execution. It ends with a semicolon.
	 * Block-a block is a group of zero or more statements between balanced braces and can be used
	 * anywhere a single statement is allowed.
	 */

	RobotMap RM = new RobotMap();
	Input IM = new Input();



	/**
	 * Now it is time to declare some constants according to the naming conventions above
	 * basically we are just mapping out the logitech controller to make our code more readable
	 * public controls access (private, default-no keyword, protected or public)
	 *	private is the most restrictive access level. methods, variables & constructors declared
	 *		private can only be accessed within the declared class itself.
	 *	default-no keyword-a variable or method declared without any access control modifier
	 *		is available to any other class in the same package
	 *	protected-variables, methods & constructors which are declared protected in a superclass
	 *		can be accessed only by the sub classes in other package or any class within the
	 *		package of the protected member's class.
	 *	public-a class, method, constructor, interface etc. declared public can be accessed from
	 *		any class in the java universe.
	 * static declares that these are Class level variables-there will only be one instance of them
	 * final makes them constants, unable to be changed later
	 * byte is their type:
	 * 		Byte data type is an 8-bit signed two's complement integer
	 *		Minimum value is -128, Maximum value is 127, default value is 0
	 *		Byte data type is used to save space since a byte is four times smaller than an int
	 */

	//Now we will declare and initialize some variables


	private int marsrot = 0, sun = 0, angle = 0;

	private double leftspeed = 0, rightspeed = 0, panspeed = 0, marsspeed = 0;

	//dbbuttons are buttons on the driver station dashboard not on the joystick
	//they are used to select which autonomous routine to run for each match
	private boolean dbbutton1 = false, dbbutton2 = false, dbbutton3 = false; 
	private boolean flipflop = true, prevgreenstate = false;
	private boolean prevredstate = false, prevbluestate = false;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {

		RM.enc.reset();
		/**We've already instantiated the compressor in the CAN portion of the class
		 * declarations. This next command just confirms that closed loop control is
		 * on even though it should already be on from the class declarations.
		 */
		RM.airPump.setClosedLoopControl (true);

	}
	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	public void autonomousInit() {
		//dbbutton1 = false; dbbutton2 = false; dbbutton3 = false; //clear residual true values
		//dbbutton1 = SmartDashboard.getBoolean("DB/Button 0", false);
		//dbbutton2 = SmartDashboard.getBoolean("DB/Button 1", false);
		//dbbutton3 = SmartDashboard.getBoolean("DB/Button 2", false);
	}
	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {

		dbbutton1 = false; dbbutton2 = false; dbbutton3 = false; //clear residual true values
		dbbutton1 = SmartDashboard.getBoolean("DB/Button 0", false);
		dbbutton2 = SmartDashboard.getBoolean("DB/Button 1", false);
		dbbutton3 = SmartDashboard.getBoolean("DB/Button 2", false);

		if (dbbutton1)	{
			RM.redRelay.set(Relay.Value.kForward);RM.blueRelay.set(Relay.Value.kForward);
		}
		if (dbbutton2)	{
			RM.whiteRelay.set(Relay.Value.kOn);
		}
		if (dbbutton3)	{
			RM.redRelay.set(Relay.Value.kForward);
			RM.blueRelay.set(Relay.Value.kForward);
			RM.whiteRelay.set(Relay.Value.kOn);
		}
	}
	public void autonomousDisable() {
		RM.redRelay.set(Relay.Value.kOff);
		RM.blueRelay.set(Relay.Value.kOff);
		for (int i=1; i<15; i++)	{
			RM.whiteRelay.set(Relay.Value.kOff);RM.whiteRelay.set(Relay.Value.kOn);
		}
		dbbutton1 = false; dbbutton2 = false; dbbutton3 = false; //clear residual true values
	}
	/**
	 * This function is called once each time the robot enters tele-operated mode
	 */
	public void teleopInit()	{

		/**Time to start the camera. These lines appear redundant because even when
		 * I rem them out the camera still works likely because it is plugged directly
		 * into the radio and doesn't go back through the roboRIO at all like it might
		 * for vision processing
		 */
		//CameraServer.getInstance().startAutomaticCapture("axis-camera");
		//CameraServer.getInstance().startAutomaticCapture();
	}
	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {

		rcubedtest();

		if (IM.stick.getRawButton(IM.LEFT_STICK_IN))	{
			lightshow ();
		}
		else 	{
			//see what the driver wants to do by polling the hand controller
			leftspeed = (IM.stick.getRawAxis(IM.LEFT_STICK_Y)); //see which direction and how far the left joystick is displaced
			leftspeed = - leftspeed; //changes the sign to change the direction

			rightspeed = (IM.stick.getRawAxis(IM.RIGHT_STICK_Y));    	
			rightspeed = -rightspeed; //changes the sign to change the direction

			panspeed = (IM.stick.getRawAxis(IM.RIGHT_STICK_X)); //gets the sideways displacement of the left joystick
			marsspeed = (IM.stick.getRawAxis(IM.LEFT_STICK_X)); //gets the left/right value of the right joystick

			//we are done polling the driver's controller. now it is time to move the motors
			setMotor (); //setMotor is just a list of set commands all located in one place to neaten up the code
			//even though we don't pass any parameters to setMotor it has access to all our speed variables

			double voltage = RM.pdp.getVoltage ();
			double blueCurrent = RM.pdp.getCurrent(14);
			double orangeCurrent = RM.pdp.getCurrent(13);
			double panCurrent = RM.pdp.getCurrent(3);
			double marsCurrent = RM.pdp.getCurrent(12);

			SmartDashboard.putNumber("today's temperature is ", RM.pdp.getTemperature());
			SmartDashboard.putNumber("PDP voltage is ", voltage);
			SmartDashboard.putNumber("Blue Motor Current is ", blueCurrent*1000);
			SmartDashboard.putNumber("Orange Motor Current is ", orangeCurrent*1000);
			SmartDashboard.putNumber("Camera pan motor current is ", panCurrent*1000);
			SmartDashboard.putNumber("Encoder Motor Current is ", marsCurrent*1000);





			/**The following set of if statements controls the LED output. First we check to see
			 * if both joysticks are forward (above a small neutral threshold.) If both sticks are
			 * forward then turn on the headlights (blue LEDs.) If both sticks are back then turn
			 * on the taillights (red LEDs.) If the right stick is forward and the left stick is
			 * neutral or aft then the robot is in a left turn and we want the backslash to light up.
			 * If the left stick is forward and the right stick is neutral or aft then we want the
			 * forward slash to illuminate.
			 */

			//If both joysticks are forward turn on the blue LEDs otherwise turn them off
			if (leftspeed > .3 & rightspeed > .3) {
				RM.blueRelay.set(Relay.Value.kForward);
			}
			else {
				RM.blueRelay.set(Relay.Value.kOff);
			}

			//If both joysticks are backward turn on the red LEDs otherwise turn them off
			/**
			 * This is an example of shorthand for the if-then-else statement.
			 * result = someCondition ? value1 : value2 ;
			 */
			//string state;
			//state = (leftspeed < -.3 & rightspeed < -.3) ? kForward : kOff ;
			//redRelay.set(Relay.Value.state);

			if (leftspeed < -.3 & rightspeed < -.3) {
				RM.redRelay.set(Relay.Value.kForward);
			}
			else {
				RM.redRelay.set(Relay.Value.kOff);
			}

			//If left wheels are faster than right wheels you are turning right
			//so turn on the forward slash half of the X
			if (leftspeed > rightspeed + .5) {
				RM.whiteRelay.set(Relay.Value.kForward);
			} 
			else if (leftspeed + .5 < rightspeed) {
				//If left wheels are slower than right wheels you are turning left
				//so turn on the reverse slash half of the X    	
				RM.whiteRelay.set(Relay.Value.kReverse);
			}
			else	{
				RM.whiteRelay.set(Relay.Value.kOff);
			}
			//sets a deadband .2 wide, centered on zero within which all LEDs are off	
			if (leftspeed <.1 & leftspeed >-.1 & rightspeed <.1 & rightspeed >-.1) {
				RM.redRelay.set(Relay.Value.kOff);
				RM.blueRelay.set(Relay.Value.kOff);
				RM.whiteRelay.set(Relay.Value.kOff);
			}
			encodershow();
			servoDemo(); //calls the method that operates the servo
		}  
	}
	/**
	 * This function is called periodically during test mode
	 */


	public void testPeriodic()	{

		if (IM.stick.getRawButton(IM.LEFT_STICK_IN))	{
			//normally we call encodershow (); if the left stick is down but for rcubed we are hijacking that input    	
			rcubedtest ();
		}
		else 	{
			//run the bag motors first
			//see what the driver wants to do by polling the hand controller
			leftspeed = (IM.stick.getRawAxis(IM.LEFT_STICK_Y)); //see which direction and how far the left joystick is displaced
			leftspeed = - leftspeed; //changes the sign to change the direction

			rightspeed = (IM.stick.getRawAxis(IM.RIGHT_STICK_Y));    	
			rightspeed = -rightspeed; //changes the sign to change the direction

			//Set the speeds on the CAN controlled Talons
			RM.leftCANDrive.set(leftspeed/10);
			RM.rightCANDrive.set(rightspeed/10);

			double airCurrent = RM.airPump.getCompressorCurrent();
			SmartDashboard.putNumber("compressor current is ", airCurrent);

			//sideToSide-forward is piston to the left
			//bigDog-forward is extended
			//doublePunch-forward is extended
			//oneTwoPunch-forward is left cylinder extended on onetwopunch

			/**We want the blue button to make the side to side piston shift
			 * to the left and the red button make the piston go back to the
			 * right. 
			 */
			if (IM.stick.getRawButton(IM.BLUE_X)) {
				RM.sideToSide.set(DoubleSolenoid.Value.kForward);
			}
			if (IM.stick.getRawButton(IM.RED_B))	{
				RM.sideToSide.set(DoubleSolenoid.Value.kReverse);
			}

			//momentary control=while left button is pressed, bigDog is extended
			if (IM.stick.getRawButton(IM.LEFT_BUTTON)) {
				RM.bigDog.set(DoubleSolenoid.Value.kForward);
			}
			else RM.bigDog.set(DoubleSolenoid.Value.kReverse);

			/** Every time we release (press and release) the green button we want double punch
			 *  to extend if it was retracted or retract if it was extended. It would be nearly
			 *  impossible to press the button and release it in the 20ms we have between scans.
			 *  So we introduce a new boolean "previous state." If the button was false last 
			 *  scan and it is still false then the operator didn't press the button and we want
			 *  to do nothing. If the button was true last scan and the button is still true then
			 *  the operator is currently pressing the button but again, there is nothing to do.
			 *  Since there is nothing to do if button was false and is still false or button was
			 *  true and is still true we don't even bother to test for either of those cases.
			 *  The real action happens when the button was false but now is true-it just got
			 *  pressed or the button was true but now it is false-the button just got released.
			 *  These are the two cases we test for. If the button was false but now is true we
			 *  only change the value of the previous state variable to true so we know for next
			 *  time. If the button was true but now is false that means it was released. First
			 *  we change the value of the previous state variable to false so we know for next
			 *  time then we change the value of the boolean variable flipflop to it's opposite
			 *  state. The last two if statements set the solenoid value to whatever direction
			 *  the flipflop variable dictates.
			 */
			if (prevgreenstate == false & IM.stick.getRawButton(IM.GREEN_A) == true)	{
				prevgreenstate = true;
			}

			if (prevgreenstate == true & IM.stick.getRawButton(IM.GREEN_A) == false)	{
				prevgreenstate = false;
				flipflop = !flipflop;
			}	        	
			if (flipflop)	{    		
				RM.doublePunch.set(DoubleSolenoid.Value.kForward);
			}
			if (!flipflop) 	{
				RM.doublePunch.set(DoubleSolenoid.Value.kReverse);
			}

			/**momentary control of oneTwoPunch-as long as yellow button is held
			 * down the right cylinder will be extended   	
			 */

			if (IM.stick.getRawButton(IM.YELLOW_Y)) {
				RM.oneTwoPunch.set(DoubleSolenoid.Value.kForward);
			}
			else RM.oneTwoPunch.set(DoubleSolenoid.Value.kReverse);
		}
	} //end of testPeriodic

	public void setMotor ()		{
		//Set the speeds on the PWM Talons first
		RM.leftDriveMotor.set(leftspeed/10); //divide by ten just to slow the rotation down
		RM.rightDriveMotor.set(rightspeed/10); //and make it easier to see
		RM.marsMotor.set(marsspeed/2);
		//Set the speeds on the CAN controlled Talons
		//RM.leftCANDrive.set(leftspeed/10);
		//RM.rightCANDrive.set(rightspeed/10);
		RM.cameraPan.set(panspeed/10);  	
	} //end of setMotor

	public void servoDemo ()	{
		if (IM.stick.getRawButton(IM.YELLOW_Y)) {
			//RM.theServo.set(1); //drives the servo to full clockwise position
			RM.theServo.setAngle(90); //allowable range 0-170 degrees 
		}

		else if (IM.stick.getRawButton(IM.GREEN_A)) {
			RM.theServo.setAngle(45);
		}
		else {
			RM.theServo.set(0); //drives the servo to full counterclockwise position
		}
	} //end of servoDemo

	public void lightshow ()	{
		if (IM.stick.getRawButton(IM.LEFT_BUTTON)) {

			if (IM.stick.getRawButton(IM.BLUE_X)) {
				RM.whiteRelay.set(Relay.Value.kReverse);
			}
			else if (IM.stick.getRawButton(IM.RED_B)) {
				RM.whiteRelay.set(Relay.Value.kForward);
			}
			else if (IM.stick.getRawButton(IM.GREEN_A)) {
				RM.whiteRelay.set(Relay.Value.kOn);
			}
			else {
				RM.whiteRelay.set(Relay.Value.kOff);
			}
		}

		else if (IM.stick.getRawAxis(IM.LEFT_TRIGGER)>=.5) {

			if (IM.stick.getRawButton(IM.BLUE_X)) {
				RM.blueRelay.set(Relay.Value.kForward);
				RM.whiteRelay.set(Relay.Value.kReverse);
			}
			else if (IM.stick.getRawButton(IM.RED_B)) {
				RM.redRelay.set(Relay.Value.kForward);
				RM.whiteRelay.set(Relay.Value.kForward);
			}
			else if (IM.stick.getRawButton(IM.GREEN_A)) {
				RM.redRelay.set(Relay.Value.kForward);
				RM.blueRelay.set(Relay.Value.kForward);
				RM.whiteRelay.set(Relay.Value.kOn);
			}
			else {
				RM.blueRelay.set(Relay.Value.kOff);
				RM.redRelay.set(Relay.Value.kOff);
				RM.whiteRelay.set(Relay.Value.kOff);
			}
		}

		else {

			if (IM.stick.getRawButton(IM.BLUE_X)) {
				RM.blueRelay.set(Relay.Value.kForward);
			}
			else if (IM.stick.getRawButton(IM.RED_B)) {
				RM.redRelay.set(Relay.Value.kForward);
			}
			else if (IM.stick.getRawButton(IM.GREEN_A)) {
				RM.redRelay.set(Relay.Value.kForward);
				RM.blueRelay.set(Relay.Value.kForward);
			}
			else {
				RM.blueRelay.set(Relay.Value.kOff);
				RM.redRelay.set(Relay.Value.kOff);
			}	
		}
	} //end of lightshow

	public void encodershow ()	{
		SmartDashboard.putString("DB/String 4","AISU ROBOFEST 2015");
		if(IM.stick.getRawButton(IM.GREEN_A)){ SmartDashboard.putBoolean("DB/LED 2", true);} else {SmartDashboard.putBoolean("DB/LED 2", false); }


		if (prevbluestate == false & IM.stick.getRawButton(IM.BLUE_X) == true)	{
			prevbluestate = true;
			sun++;
		}

		if (prevbluestate == true & IM.stick.getRawButton(IM.BLUE_X) == false)	{
			prevbluestate = false;
		}
		if (prevredstate == false & IM.stick.getRawButton(IM.RED_B) == true)	{
			prevredstate = true;
			sun--;
		}

		if (prevredstate == true & IM.stick.getRawButton(IM.RED_B) == false)	{
			prevredstate = false;
		}
		double doublesun = sun;
		SmartDashboard.putNumber("the sun is ", doublesun);

		//if sun is positive rotate mars in reverse
		if (sun > .5) {
			marsspeed = -1; setMotor();
		} //end of positive sun

		//if sun is negative rotate mars forward
		else if (sun < -.5) {
			marsspeed = +1; setMotor();
		}//end of negative sun

		else {
			marsspeed = 0; setMotor();//stop the encoder motor 
			RM.enc.reset();//zero out the encoder counter ready for next time
		}

		marsrot = RM.enc.get();
		sun = sun + (marsrot/200);
		SmartDashboard.putNumber("motor rots ", RM.enc.get());


	} //end of encodershow

	public void rcubedtest ()	{

		/**
		 * There are three commands for the servo, "set" and "setAngle" and "setRaw"
		 * when using "set" allowable values range from ????? to ???
		 * when using "setAngle" allowable values are
		 * when using "setRaw" allowable values are 
		 *     	
		 */
		//drive the robot into position



		//light up the aiming laser
		if (IM.controllerSlot2.getRawButton(IM.LEFT_BUTTON) )	{
			RM.whiteRelay.set(Relay.Value.kForward);    		
		}
		else
			RM.whiteRelay.set(Relay.Value.kOff);

		//set the shooting tube angle
		if (IM.controllerSlot2.getRawButton(IM.YELLOW_Y)) {
			angle = 300;
		}
		else if (IM.controllerSlot2.getRawButton(IM.BLUE_X)) {
			angle = 500;
		}
		else if (IM.controllerSlot2.getRawButton(IM.GREEN_A)) {
			angle = 800;
		} 
		//by never "set"ting the servo to zero it remains at last angle until a new one is selected


		RM.rCubedAngle.setRaw(angle);
		
		RM.rCubedAngle.set(0);

		
			
		
	}//end of rcubed test


} //end of public class Robot

