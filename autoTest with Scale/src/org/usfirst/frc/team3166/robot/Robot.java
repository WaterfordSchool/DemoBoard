/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3166.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3166.robot.commands.ExampleCommand;
import org.usfirst.frc.team3166.robot.subsystems.ExampleSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final ExampleSubsystem kExampleSubsystem
			= new ExampleSubsystem();
	public static OI m_oi;
	
	String gameData, robotInitPos;//added by LW 2/6
	int desiredPath;//added by LW 2/6
	double autoDelay;//added by LW 2/6

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		m_chooser.addDefault("Default Auto", new ExampleCommand());
		chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
	}//closes method robotInit

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}//closes method disabledInit

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}//closes method disabledPeriodic

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();		
		}//closes first if statement
		
		//Begin code added by LW 2/6
		//read the driver station to get the plate assignments
	gameData = DriverStation.getInstance().getGameSpecificMessage();
		//read the dashboard to get the robot's initial starting position and amount of delay
	robotInitPos = SmartDashboard.getString("Where am I starting? L, C, or R?", "X");
	autoDelay = SmartDashboard.getNumber("time delay in seconds ", 0);
	
	SmartDashboard.putString("The robot is starting on the ", robotInitPos);
	SmartDashboard.putNumber("time delay in seconds ", autoDelay);
	SmartDashboard.putString("The game specific data is ", gameData);
	
		//nested if statements figure out which path to take
	if ((robotInitPos.charAt(0) == 'L')||(robotInitPos.charAt(0) == 'l')) {  //robot is starting on Left
		if (gameData.charAt(0) == 'L') {  //robot starts on Left and near switch plate assignment is Left
			if (gameData.charAt(1) == 'L') { //robot starts on left, near switch is left, scale is left
				desiredPath = 1;}
			else { //robot starts on left, near switch is left, scale is right
				desiredPath = 2;}
		} else {  //robot is starting on Left and near switch is right
			if (gameData.charAt(1) == 'L') { //robot starts on left, near switch is right, scale is left
				desiredPath = 3;}
			else { //robot starts on left, near switch is right, scale is right
				desiredPath = 4;}		
		} //closes if robot starts on the left
		
	} else if ((robotInitPos.charAt(0) == 'C')||(robotInitPos.charAt(0) == 'c')) { //robot is starting in the center
		if (gameData.charAt(0) == 'L') {  //robot starts in the center and near switch is Left
			if (gameData.charAt(1) == 'L') { //robot starts in the center, near switch is left, scale is left
				desiredPath = 5;}
			else { //robot starts in the center, near switch is left, scale is right
				desiredPath = 6;}
		} else {  //robot is starts in the center and near switch is right
			if (gameData.charAt(1) == 'L') { //robot starts in the center, near switch is right, scale is left
				desiredPath = 7;}
			else { //robot starts in the center, near switch is right, scale is right
				desiredPath = 8;}	
		} //closes if robot starts in the center
		
	} else if ((robotInitPos.charAt(0) == 'R')||(robotInitPos.charAt(0) == 'r')) { //robot is starting on Right
		if (gameData.charAt(0) == 'L') {  //robot starts on Right and near switch plate assignment is Left
			if (gameData.charAt(1) == 'L') { //robot starts on Right, near switch is left, scale is left
				desiredPath = 9;}
			else { //robot starts on Right, near switch is left, scale is right
				desiredPath = 10;}
		} else {  //robot is starts on the Right and near switch is right
			if (gameData.charAt(1) == 'L') { //robot starts on right, near switch is right, scale is left
				desiredPath = 11;}
			else { //robot starts on right, near switch is right, scale is right
				desiredPath = 12;}	
		} //closes if robot starts on the right
		
	} else { //the robot starting position was neither L, l, C, c, R, nor r
		desiredPath = -1;
	}	
		//We wait until it is our turn to move
	Timer.delay(autoDelay);
	SmartDashboard.putNumber("waiting ", autoDelay);
	
		//switch statement moves us along the correct path
	switch (desiredPath) {
		case 1:		//robot starts on the left, near switch is left, scale is left
			System.out.println("robot starts on the left, near switch is left, scale is left");
			System.out.println("go straight");
			System.out.println("pivot right");
			System.out.println("dump the cube");
			break;
		case 2:		//robot starts on the left, near switch is left, scale is right
			System.out.println("robot starts on the left, near switch is left, scale is right");
			System.out.println("arc right");
			System.out.println("drive straight"); //move parallel to the driver station wall
			System.out.println("pivot left");
			System.out.println("drive straight");
			System.out.println("pivot left");
			System.out.println("dump the cube");
			break;
		case 3:		//robot starts on the left, near switch is right, scale is left
			System.out.println("robot starts on the left, near switch is right, scale is left");
			System.out.println("arc left"); //addSequential command
			System.out.println("drive straight"); //addSequential command
			System.out.println("pivot right"); //addSequential command
			System.out.println("drive straight"); //addSequential command
			System.out.println("pivot right"); //addSequential command
			System.out.println("dump the cube"); //addSequential command
			break;
		case 4:		//robot starts on the left, near switch is right, scale is right
			System.out.println("robot starts on the left, near switch is right, scale is right");
			System.out.println("arc right"); //addSequential command
			System.out.println("drive straight"); //addSequential command
			System.out.println("pivot left"); //addSequential command
			System.out.println("drive straight"); //addSequential command
			System.out.println("pivot left"); //addSequential command
			System.out.println("dump the cube"); //addSequential command
			break;
		case 5:		//robot starts in the center, near switch is left, scale is left
			System.out.println("robot starts in the center, near switch is left, scale is left");
			System.out.println("arc left"); //addSequential command
			System.out.println("drive straight"); //addSequential command
			System.out.println("pivot right"); //addSequential command
			System.out.println("drive straight"); //addSequential command
			System.out.println("pivot right"); //addSequential command
			System.out.println("dump the cube"); //addSequential command
			break;
		case 6:		//robot starts in the center, near switch is left, scale is right
			System.out.println("robot starts in the center, near switch is left, scale is right");
			System.out.println("drive straight"); //addSequential command
			System.out.println("pivot left"); //addSequential command
			System.out.println("drive straight"); //addSequential command
			System.out.println("dump the cube"); //addSequential command
			break;
		case 7:		//robot starts in the center, near switch is right, scale is left
			System.out.println("robot starts in the center, near switch is right, scale is left");
			break;
		case 8:		//robot starts in the center, near switch is right, scale is right
			System.out.println("robot starts in the center, near switch is right, scale is right");
			break;
		case 9:		//robot starts on the right, near switch is left, scale is left
			System.out.println("robot starts on the right, near switch is left, scale is left");
			break;
		case 10:	//robot starts on the right, near switch is left, scale is right
			System.out.println("robot starts on the right, near switch is left, scale is right");
			break;
		case 11:	//robot starts on the right, near switch is right, scale is left
			System.out.println("robot starts on the right, near switch is right, scale is left");
			break;
		case 12:	//robot starts on the right, near switch is right, scale is right
			System.out.println("robot starts on the right, near switch is right, scale is right");
			break;
		default:
			//what do we want the robot to do if no valid input??
			//drive straight to get across the auto line for minimum points
			System.out.println("No valid input. Drive straight to cross the auto line for minimum points");
			break;
	} //closes our switch statement
} //closes autonomousInit method

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}//closes autonomousPeriodic method

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}//closes teleopInit method

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}//closes teleopPeriodic method

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}//closes testPeriodic method
}//closes Robot class