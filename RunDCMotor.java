import rxtxrobot.*;
public class RunDCMotor {
	static RXTXRobot r = new ArduinoNano();
	    
	public static void main(String[] args) {
	        
		r.setPort("/dev/tty.usbmodem14501"); // Set port to your own computer port
		 
		r.connect();
	                
	                //move 3m
		//r.runTwoPCAMotor(0, 200, 1, -200, 3000);
		//r.sleep(3000);
		RotateRobotNegative();
		r.sleep(3000); //puts robot to "sleep" for 3 seconds, to make sure that the two Instructions don't overlap
		RotateRobotPositive();
	}
	    
	public static void RotateRobotPositive() {
	    r.runTwoPCAMotor(0, 180, 1, 180, 800); //rotates the robot 90 degrees when battery is at 13.0 V
	    //motor strength 180, channels 0 and 1, time: .8 seconds
	}
	    
	public static void RotateRobotNegative() {
	    r.runTwoPCAMotor(0, -150, 1, -150, 800); //rotates the robot approximately -90 degrees when battery is at 13.0 V
	    //motor strength 150, channels 0 and 1, time: .8 seconds
	}
}
//edit made by Trey S.
