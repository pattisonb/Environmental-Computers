import rxtxrobot.*;

public class RunDCMotor {
    static RXTXRobot r = new ArduinoNano();
    
    public static void main(String[] args) {
        
		r.setPort("/dev/tty.usbmodem14301"); // Set port to your own computer port
	 
		r.connect();
                
                //move 3m
		r.runTwoPCAMotor(0, 200, 1, 200, 5000);
    }
    
    public static void RotateRobotPositive() {
        r.runTwoPCAMotor(0, 200, 1, -200, 5000);
    }
    
    public static void RotateRobotNegative() {
        r.runTwoPCAMotor(0, -200, 1, 200, 5000);
    }
}
