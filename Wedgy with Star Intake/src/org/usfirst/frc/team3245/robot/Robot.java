package org.usfirst.frc.team3245.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.*;
//git comment
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	//two contollers, one for driver, one for accessory motor
	Joystick drivercontrol = new Joystick (0);
	Joystick operatorcontrol = new Joystick (1);
	//
	Talon leftdrive1 = new Talon (0);
	Talon leftdrive2 = new Talon (1);
	Talon rightdrive1 = new Talon (3);
	Talon rightdrive2 = new Talon (4);
	Talon accessorymotor1 = new Talon (6);
	
	double leftspeed = 0, rightspeed = 0, accessoryspeed = 0;

	PowerDistributionPanel PDP = new PowerDistributionPanel (0);
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
	
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		leftspeed = drivercontrol.getRawAxis(1);
		rightspeed = -drivercontrol.getRawAxis(3);
		
		leftdrive1.set(-leftspeed);
		leftdrive2.set(-leftspeed);
		rightdrive1.set(-rightspeed);
		rightdrive2.set(-rightspeed);
		
		accessoryspeed = operatorcontrol.getRawAxis(3);
		accessorymotor1.set(accessoryspeed * 0.2);
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

