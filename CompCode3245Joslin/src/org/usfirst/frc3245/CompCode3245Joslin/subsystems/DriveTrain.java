// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3245.CompCode3245Joslin.subsystems;

import org.usfirst.frc3245.CompCode3245Joslin.RobotMap;
import org.usfirst.frc3245.CompCode3245Joslin.commands.*;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class DriveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController leftFront = RobotMap.driveTrainLeftFront;
    private final SpeedController leftRear = RobotMap.driveTrainLeftRear;
    private final SpeedController rightFront = RobotMap.driveTrainRightFront;
    private final SpeedController rightRear = RobotMap.driveTrainRightRear;
    private final RobotDrive robotDrive41 = RobotMap.driveTrainRobotDrive41;
    private final AnalogGyro gyro = RobotMap.driveTrainGyro;
    private final Encoder leftEncoder = RobotMap.driveTrainleftEncoder;
    private final Encoder rightEncoder = RobotMap.driveTrainrightEncoder;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    /**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	public void log() {
		SmartDashboard.putNumber("Left Distance", leftEncoder.getDistance());
		SmartDashboard.putNumber("Right Distance", rightEncoder.getDistance());
		SmartDashboard.putNumber("Left Speed", leftEncoder.getRate());
		SmartDashboard.putNumber("Right Speed", rightEncoder.getRate());
		SmartDashboard.putNumber("Gyro", gyro.getAngle());
	}

	/**
	 * Tank style driving for the DriveTrain.
	 * 
	 * @param left
	 *            Speed in range [-1,1]
	 * @param right
	 *            Speed in range [-1,1]
	 */
	public void drive(double left, double right) {
		robotDrive41.tankDrive(left, right);
	}

	/**
	 * @param joy
	 *            The ps3 style joystick to use to drive tank style.
	 */
	public void drive(Joystick driver) {
		drive(-driver.getY(), -driver.getAxis(AxisType.kThrottle));
	}

	/**
	 * @return The robots heading in degrees.
	 */
	public double getHeading() {
		return gyro.getAngle();
	}

	/**
	 * Reset the robots sensors to the zero states.
	 */
	public void reset() {
		gyro.reset();
		leftEncoder.reset();
		rightEncoder.reset();
	}

	/**
	 * @return The distance driven (average of left and right encoders).
	 */
	public double getDistance() {
		return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
	}
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new TankDriveWithJoystick());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}
