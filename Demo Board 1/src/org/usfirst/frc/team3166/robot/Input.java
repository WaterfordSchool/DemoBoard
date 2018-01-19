package org.usfirst.frc.team3166.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Input {
	
	/**
	 * Logitech joystick must be in USB slot 0 on the driver's station
	 * it doesn't matter what physical USB slot on the classmate it is plugged into	
	 */
		Joystick stick = new Joystick (0);
		Joystick controllerSlot2 = new Joystick(2);
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
	
	public static final byte 
		//these joystick controls show up on the driver station display as Buttons
		GREEN_A = 1,
		RED_B = 2,
		BLUE_X = 3,
		YELLOW_Y = 4,
		LEFT_BUTTON = 5,
		RIGHT_BUTTON = 6,
		BACK = 7,
		START = 8,
		LEFT_STICK_IN = 9,
		RIGHT_STICK_IN = 10,
		//these joystick controls show up on the driver station display under Axes
		LEFT_STICK_X = 0,
		LEFT_STICK_Y = 1,
		LEFT_TRIGGER = 2,
		RIGHT_TRIGGER = 3,
		RIGHT_STICK_X = 4,
		RIGHT_STICK_Y = 5;
	
   /**notice that LEFT_STICK_Y is one and GREEN_A is also one
	* RIGHT_STICK_Y gets assigned the same value (5) as LEFT_BUTTON
	* The reason it's not redundant to have constants with the same value is because
	* some are buttons and some are sticks. When we query the logitech controller
	* sticks return a decimal number between -1 and 1. Buttons return a value of true
	* (pressed) or false (not pressed.) These integer variables are only used to 
	* make reading the code clearer about which input on the controller we are querying.
	*/

} //end of public class Input
