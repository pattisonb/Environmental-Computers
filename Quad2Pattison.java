import rxtxrobot.*;

public class Quadrant2Auto {
    static RXTXRobot r = new ArduinoNano();
    
    public static void main(String[] args) {
        
		r.setPort("/dev/tty.usbmodem14301"); 
		r.connect();
                   r.runPCAServo(9, 45);
                   r.sleep(300);
                r.runTwoPCAMotor(14, 235, 15, -180, 2600);//move straight forward
                //RotateRobotPositive(100);
                r.sleep(1500);
                
                FindBeacon('S');//find beacon
                r.sleep(2000);
                r.runTwoPCAMotor(14, 235, 15, -170, 1400);//go straight to the salamander zone
                RotateRobotPositive(75);//turn right
            
                r.sleep(200);
                r.runTwoPCAMotor(14, 235, 15, -170, 925);//go straight to collect the salamanders
                r.sleep(2500);
                r.runPCAServo(9, 180);//move the arm down to secure the salamanders                
                r.sleep(1000);

                r.runTwoPCAMotor(14, -235, 15, 235, 1800);
                r.sleep(200);
                FindBeacon('G');
                r.runTwoPCAMotor(14, 245, 15, -170, 3100);
                
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
                RotateRobotPositive(50);
                r.sleep(1000);
                }
                }
}
    
