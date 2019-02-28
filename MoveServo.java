// This example moves each servo individually.  While this method should be used to move one servo individually, it is recommended to use moveBothServos if both servos must be moved simultaneously
import rxtxrobot.*;

public class MoveServo
{
	public static void main(String[] args)
	{
		RXTXRobot r = new ArduinoNano(); // Create RXTXRobot object
		r.setPort("COM3"); // Set the port to COM3
		r.setVerbose(true); // Turn on debugging messages
		r.connect();
		
		//Move 180 Servo on channel 0 to 50 degrees
		r.runPCAServo(0, 50);

		//Move Continuous Servo on channel 1 at 90 speed	
		r.runPCAContServo(1, 90); 

		//Move 180 Servo on channel 2 to 50 degrees for 1000 milliseconds
		r.runPCATimedServo(2, 50, 1000);
		r.close();
		
		//turn the servo for a total of 180 degrees for 5 degrees at a time
		//if string returned from the beacon matches the designated string of the beacon, stop the servo from turning 
		for(int i = 0; i <= 180; i+=5) {
			int angleTurned = 0;
			r.runPCAServo(0, i);
			char designatedCode = ' '; //input the designated letter for the beacons on the spot
			char beacon = r.getIRChar(); //the code received from the beacon
			if(designatedCode == beacon) {
				angleTurned = i; // return the angle that the servo has turned
			}
		}
		//now write codes that turn the motors according to "angleTurned" 
		
		
	}
}
