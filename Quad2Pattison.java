import rxtxrobot.*;

public class Quadrant2Auto {
    static RXTXRobot r = new ArduinoNano();
    
    public static void main(String[] args) {
        
		r.setPort("/dev/tty.usbmodem14301"); 
		r.connect();
                   r.runPCAServo(9, 45);
                   r.sleep(300);
                r.runTwoPCAMotor(14, 200, 15, -170, 2900);//move forward for x amount of time
                //RotateRobotPositive(100);
                r.sleep(1500);
                RotateRobotPositive(150);
                r.sleep(1500);
                FindBeacon('S');
                r.sleep(2000);
                RotateRobotPositive(50);
                r.sleep(750);
                r.runTwoPCAMotor(14, 220, 15, -200, 1400);            
                r.sleep(200);
                r.runTwoPCAMotor(14, 225, 15, -200, 700);
                r.sleep(1500);
                r.runPCAServo(9, 180);                
                r.sleep(1000);

                r.runTwoPCAMotor(14, -235, 15, 235, 1900);
                r.sleep(2000);
                FindBeacon('G');
                //RotateRobotPositive(415);
                r.runTwoPCAMotor(14, 245, 15, -180, 900);
                r.sleep(1500);
                r.runTwoPCAMotor(14, 245, 15, -190, 1100);
                r.runPCAServo(9, 45);
                
                r.close();            
    }
    
    public static void RotateRobotPositive(int time) {
	    r.runTwoPCAMotor(14, 180, 15, 180, time); //rotates the robot 90 degrees when battery is at 13.0 V //1325 for 90
	    //motor strength 180, channels 0 and 1, time: .8 seconds
	}
	    
    public static void RotateRobotNegative(int time) {
	    r.runTwoPCAMotor(14, -150, 15, -150, time); //rotates the robot approximately -90 degrees when battery is at 13.0 V //850 for 90
	    //motor strength 150, channels 0 and 1, time: .8 seconds
	}
    public static void FindBeacon (char beaconChar) {
                char beaconInput = '0';
                char beaconOutput = beaconChar; //character sent out by beacon CHANGE THIS!!!
                for(;;) {
                            beaconInput = r.getIRChar();
                            System.out.println(beaconInput);
			if(beaconInput == beaconOutput) {
                            break;
			}
                r.sleep(200);
                r.runTwoPCAMotor(14, 140, 15, 140, 130);
                r.sleep(1000);
                }
                }
}
    
