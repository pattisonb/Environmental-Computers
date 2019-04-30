import rxtxrobot.*;

public class Quadrant4Auto {
    static RXTXRobot r = new ArduinoNano();
    
    public static void main(String[] args) {
                r = new ArduinoUno();
		r.setPort("COM3"); 
		r.connect();
		
				r.runTwoPCAMotor(14, 200, 15, -200, 6000);//stay on the ramp
                r.runPCAServo(9, 45);
                r.sleep(1000);
                r.runPCAServo(8, 0);
            	RotateRobotNegative(90);
                r.sleep(500);
                FindBeaconPos('K');//find 'K'
                r.sleep(800);
                
                //DRIVE TO SITE: MIGHT NEED TO INCREASE OR DECREASE TIME
                //r.runTwoPCAMotor(14, 235, 15, -200, 100); //drive to temp site (400 at 13.2V)
                //r.sleep(3000);
                
                //ADJUST FOR SITE: MIGHT NEED TO INCREASE OR DECREASE TURN ANGLE
                RotateRobotPositive(365); //adjust for temp site (185 at 13.3V, 225 at 13.8V)
                r.sleep(500);
                
                
                r.runTwoPCAMotor(14, 235, 15, -200, 600); //finsih driving to temp site
                r.sleep(1500);
               //take temp
                r.runPCAServo(8, 180);
                r.sleep(3000);
                double celsius = (getThermistorReading() - 684.7186471)/-6.93063423; 
                System.out.printf("In Degrees Celsius: %.2f\n", celsius);
                r.runPCAServo(8, 0);
                
                r.sleep(1000);
                
                //BACK UP: MIGHT NEED TO INCREASE OR DECREASE TIME
                r.runTwoPCAMotor(14, -215, 15, 260, 160); //back up a little
                r.sleep(1000);
                
                //ROTATE FOR WALL: MIGHT NEED TO INCREASE OR DECREASE TURN ANGLE
                RotateRobotNegative(220); //(300 at 13.3 V, 250 at 13.7 V) roatte less than 90 so it can start to find 'N' at a closer distance; aka drive diagonally across the quadrant
                r.sleep(1000);
                
                //DRIVE TO WALL: MIGHT NEED TO INCREASE OR DECREASE TIME
                r.runTwoPCAMotor(14, 235, 15, -180, 2450); //drive forwards to wall (2450 at 13.7V, 3050 at 13.2V, 3350 at 13.1V)
                r.sleep(2000);
                
                FindBeaconPos('N'); //find n
                
                //DRIVE TO N: MIGHT NEED TO INCREASE OR DECREASE TIME
                r.runTwoPCAMotor(14, 235, 15, -180, 1500); //drive to n (1400 at 13.5V, 1800 at 13.3V)
                r.sleep(5000);
              
                r.runTwoPCAMotor(14, -215, 15, 260, 55); //back up a little but
                
                //TURN TO BRIDGE: MIGHT NEED TO INCREASE OR DECREASE ANGLE
                RotateRobotPositive(990); //rotate to face bridge (950 at 13.3V, 970 at 13.8)
                r.sleep(2000);
                r.runTwoPCAMotor(14, 215, 15, -260, 30); //set up to drive over bridg (backwards)
                r.sleep(500); 
                r.runTwoPCAMotor(14, 235, 15, -200, 800); //setting up for drive over bridge (forwards)
                
                //MIGHT NEED TO INCREASE RIGHT WHEEL
                r.runTwoPCAMotor(14, 400, 15, -425, 25); //get over ledge
                r.sleep(500);
                r.runTwoPCAMotor(14, 550, 15, -720, 1000);
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
                RotateRobotPositive(30);
                r.sleep(1000);
                }
                }
}
