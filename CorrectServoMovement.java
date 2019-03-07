import rxtxrobot.*;

public class CorrectServoMovement {
    static RXTXRobot r = new ArduinoUno();
    public static void main(String[] args)
	{
		
		r.setPort("/dev/tty.usbmodem14301");
		r.setVerbose(true); // Turn on debugging messages
		r.connect();
                int[] angles = {0, 0, 0}; //array of angles detected
                char beaconInput = '0';
                char beaconOutput = 'G'; //character sent out by beacon
                int avgAngle = 0;
                int rotationAngle = 0;
                int timeAngle = 0; //time based on rotaitonAngle
                
                for(int i = 30; i <= 180; i+=10) {
                    r.runPCAServo(0, i);
                    for (int j = 0; j < 3; j ++) { //try to get angle five times to avoid random ir signals
                            beaconInput = r.getIRChar(); //the code received from the beacon
			if(beaconOutput == beaconInput) {
                            angles[j] = i;
			}
                }
                            if (angles[0] == angles[1] && angles[1] == angles[2] && beaconInput != '0') {
                            avgAngle = i;
                                if (angles[0] == 0) {
                                    break;
                                }
                            }
                            
                }
                System.out.println(avgAngle);
                rotationAngle = 90 - avgAngle;
                r.runPCAServo(0, 90);
                r.sleep(400);
                if (rotationAngle > 0) {
                    timeAngle = ((1325)/(90/rotationAngle));
                    r.runPCAServo(0, rotationAngle);
                    r.sleep(2000);
                    RotateRobotPositive(timeAngle);
                    r.sleep(2000);
                    r.runPCAServo(0, 90);
                    r.runTwoPCAMotor(15, -200, 14, 260, 2000);
                }
                if (rotationAngle < 0) {
                    rotationAngle = rotationAngle * -1;
                    timeAngle = ((850)/(120/rotationAngle));
                    System.out.println(rotationAngle);
                    System.out.print(timeAngle);
                    r.sleep(2000);
                    RotateRobotNegative(timeAngle);
                    r.sleep(2000);
                    r.runPCAServo(0, 90);
                    r.runTwoPCAMotor(15, -200, 14, 260, 2000);
                }
                else {
                r.runPCAServo(0, 90);
                r.sleep(300);
                r.runTwoPCAMotor(15, -200, 14, 260, 2000);
                }
                System.exit(0);
        }
    	public static void RotateRobotPositive(int time) {
	    r.runTwoPCAMotor(14, 180, 15, 180, time); //rotates the robot 90 degrees when battery is at 13.0 V //1325 for 90
	    //motor strength 180, channels 0 and 1, time: .8 seconds
	}
	    
	public static void RotateRobotNegative(int time) {
	    r.runTwoPCAMotor(14, -150, 15, -150, time); //rotates the robot approximately -90 degrees when battery is at 13.0 V //850 for 90
	    //motor strength 150, channels 0 and 1, time: .8 seconds
	}
}
