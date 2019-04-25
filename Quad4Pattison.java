import rxtxrobot.*;

public class Quadrant4Auto {
    static RXTXRobot r = new ArduinoNano();
    
    public static void main(String[] args) {
                r = new ArduinoUno();
		r.setPort("/dev/tty.usbmodem14301"); 
		r.connect();
                
                r.runPCAServo(9, 45);
                r.runPCAServo(8, 0);
		r.runTwoPCAMotor(14, -215, 15, 235, 600); //drive backwards after ramp
                r.sleep(500);

                FindBeaconPos('K');
                r.sleep(200);
                r.sleep(500);
                r.runTwoPCAMotor(14, 235, 15, -200, 1200); //drive to temp site
                r.sleep(500);
                RotateRobotPositive(75); //adjust for temp site
                r.sleep(500);
                r.runTwoPCAMotor(14, 235, 15, -200, 600); //finsih driving to temp site
                r.sleep(1500);
                r.runPCAServo(8, 180);
                r.sleep(3000);
                double celsius = (getThermistorReading() - 677.7186471)/-6.93063423; 
                System.out.printf("In Degrees Celsius: %.2f\n", celsius);
                r.runPCAServo(8, 0);
                r.sleep(1000);
                r.runTwoPCAMotor(14, -215, 15, 260, 15); //back up a little but
                r.sleep(1000);
                RotateRobotNegative(450); //roatte 90
                r.sleep(1000);
                r.runTwoPCAMotor(14, 235, 15, -180, 2920); //drive forwards to wall
                r.sleep(1000);
                FindBeaconPos('N'); //find n
                r.runTwoPCAMotor(14, 235, 15, -180, 2200); //drive to n
                r.sleep(1000);
              
                
		RotateRobotPositive(845); //rotatte to face bridge
		r.sleep(2000);
                r.runTwoPCAMotor(14, -215, 15, 260, 30); //set up to drive over bridg (backwards)
                r.sleep(500); 
                r.runTwoPCAMotor(14, 235, 15, -200, 100); //setting up for drive over bridge (forwards)
                  r.runTwoPCAMotor(14, 235, 15, -200, 25);
                r.sleep(500);
                r.runTwoPCAMotor(14, 400, 15, -425, 25); //get over ledge
                r.sleep(500);
                r.runTwoPCAMotor(14, 450, 15, -520, 1000);
                r.sleep(1000);                
                r.runTwoPCAMotor(14, 235, 15, -200, 900);
                r.sleep(500);
                
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
    public static double getThermistorReading() {
        int sum = 0;
        int readingCount = 10;
        
        for (int i = 0; i < readingCount; i++) {
            r.refreshAnalogPins();
            int reading = r.getAnalogPin(0).getValue();
            sum += reading;
        }
        return sum / readingCount;
    }
        public static void FindBeaconNeg (char beaconChar) {
                char beaconInput = '0';
                char beaconOutput = beaconChar; //character sent out by beacon CHANGE THIS!!!
                for(;;) {
                            beaconInput = r.getIRChar();
                            System.out.println(beaconInput);
			if(beaconInput == beaconOutput) {
                            break;
			}
                r.sleep(200);
                RotateRobotNegative(50);
                r.sleep(1000);
                }
                }
                public static void FindBeaconPos (char beaconChar) {
                    r.refreshDigitalPins();
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
