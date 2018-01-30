package org.usfirst.frc.team3166.robot;

import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

public class RobotMap {
	
/**The following blocks create (instantiate) and initialize all the objects
 * wired to the roboRIO and the PCM. This could also be done in a two line process
 * where the first line instantiates the object and later you set it's initial value.
 * private Talon leftDriveMotor; //creates a new talon object named leftDriveMotor
 * leftDriveMotor = new Talon (1); //tells that left drive motor is wired to PWM pin 1
 */
	
//devices attached to the PWM pins on the roboRIO
	Talon rightDriveMotor = new Talon (0);	
	Talon leftDriveMotor = new Talon (1);	
	Talon marsMotor = new Talon (7);
	Servo theServo = new Servo (9);//declares a servo object named theServo
	Servo rCubedAngle = new Servo (3);//used to test rookie rcubed servo
		
//the encoder feedback comes back to DIO pins 0 and 1 on the roboRIO
//false tells the encoder to not invert the counting direction
//k2X refers to the accuracy we desire (choices are 1, 2 & 4)
	Encoder enc = new Encoder (0,1,false,Encoder.EncodingType.k1X);
	
//declaring all the objects connected to the Relay pins on the roboRIO
	Relay blueRelay = new Relay (3); //blueRelay is the Spike relay connected to the outside of the W
	Relay redRelay = new Relay (2); //redRelay is the Spike relay connected to the inside of the W
	Relay whiteRelay = new Relay (1); //whiteRelay is the Spike relay connected to the white X LEDs
	
/**
 * The CAN network starts at the roboRIO and is physically wired through the PCM
 * (if installed) then through three motor controllers (Talon SRXs) then it terminates at
 * a 120 ohm resistor conveniently built into the power distribution panel.
 * If only one component of a type, leave the node number at the default of zero. (PDP, PCM)
 * If more than one (motor controllers, second PCM?) each must have a unique node number.
 * It is not necessary to create an instance of the PDP to use it, but since we want to
 * read values from the power distribution panel we need to create an instance of it
 * Also, it is not necessary to create an instance of the compressor. Any instance of a
 * solenoid automatically creates the compressor. I do it here for clarity and an example	
 */
	
	PowerDistributionPanel pdp = new PowerDistributionPanel();


//since we only have one PCM we leave the node number at the default of zero
//don't get confused, we are declaring a compressor attached to a PCM node #0
	Compressor airPump = new Compressor (12);
		
//Creating all the motors controllers on the CAN and declaring their node numbers
	WPI_TalonSRX rightCANDrive = new WPI_TalonSRX (32);	
	WPI_TalonSRX leftCANDrive = new WPI_TalonSRX (45);
	WPI_TalonSRX cameraPan = new WPI_TalonSRX(31);
	
//now it is time to declare which solenoids are attached to which pins on the PCM
	DoubleSolenoid sideToSide = new DoubleSolenoid (12,1,0);
	DoubleSolenoid bigDog = new DoubleSolenoid (12,6,7);
	DoubleSolenoid doublePunch = new DoubleSolenoid (12,4,5);
	DoubleSolenoid oneTwoPunch = new DoubleSolenoid (12,2,3);

} //end of public class RobotMap
