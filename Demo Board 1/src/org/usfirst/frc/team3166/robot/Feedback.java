package org.usfirst.frc.team3166.robot;

import edu.wpi.first.wpilibj.Encoder;

public class Feedback {
	
	//the encoder feedback comes back to DIO pins 0 and 1 on the roboRIO
	//false tells the encoder to not invert the counting direction
	//k2X refers to the accuracy we desire (choices are 1, 2 & 4)
		Encoder enc = new Encoder (0,1,false, Encoder.EncodingType.k1X);
		
	//The encoder not attached to a motor provides feedback	about 
	//whatever it gets attached to. right now we just print it's value
	//to the smart dashboard and the shuffleboard. it is attched at DIO pins 8 & 9
		Encoder Grayhill = new Encoder (8,9,false, Encoder.EncodingType.k1X);

}
