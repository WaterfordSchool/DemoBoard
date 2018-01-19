package org.usfirst.frc.team3166.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	RobotDrive myRobot = new RobotDrive(1,2);
	Joystick stick = new Joystick(0);
	Timer timer = new Timer();

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

	//Now we will declare and initialize some variables

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {

	}
	
	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	public void autonomousInit() {
		timer.reset();
		timer.start();
	}
	
	/**
	 * This function is run periodiclly during autonomous mode
	 */
	public void autonomousPeriodic() {
		// Drive for 2 seconds
		if (timer.get() < 2.0) {
			myRobot.drive(-0.1, 0.0); //drive forward half speed
		} else {
			myRobot.drive(0.0, 0.0); //top robot
		}
	}
	/**
	 * This function is called once each time the robot enters tele-operated mode
	 */
	public void teleopInit()	{

	}
	
	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		myRobot.arcadeDrive(stick); 
	}
	
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic()	{
		LiveWindow.run();
	} //end of testPeriodic


} //end of public class Robot

