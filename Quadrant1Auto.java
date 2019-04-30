import rxtxrobot.*;

public class Quadrant1Auto {
    static RXTXRobot r = new ArduinoNano();
    
    public static void main(String[] args) {
        
		r.setPort("COM3"); 
		r.connect();
                
				r.runTwoPCAMotor(14, 360, 15, -385, 400);//adjusted 4/21/19 6:47PM
				r.sleep(3000);
				r.runTwoPCAMotor(14, 225, 15, -121, 2500);//adjusted 4/21/19 6:47PM
				r.sleep(3000);
				RotateRobotNegative(700);  // MIGHT NEED TO CHANGE TO POSITIVE DEPENDING ON ANGLE OF RAMP
				r.sleep(2000);
				
				//
				//r.runTwoPCAMotor(14, -175, 15, 203, 400);//backwards
				//r.sleep(2000);
				
				
//				RotateRobotPositive(150);
//				r.sleep(1000);
				
				//****ADJUSTING AND FINDING BEACON****
				//r.runTwoPCAMotor(14, 230, 15, -182, 1500);
                //r.sleep(2000);
                r.runTwoPCAMotor(14, -175, 15, 203, 200);
                r.sleep(1000);
       
           
                
                RotateRobotNegative(80);    
                //***GOING UP THE RAMP*******
                //RotateRobotNegative(1200);
                r.runTwoPCAMotor(14, 435, 15, -400, 1200);
				r.runTwoPCAMotor(14, 200, 15, -160, 300);
                r.runTwoPCAMotor(14, 200, 15, -12000, 5000);
                r.runTwoPCAMotor(14, 700, 15, -400, 3000);
                r.sleep(10000);
                
                
                
                //THIS IS IF IT GETS AT THE TOP OF THE FREAKING VOLCANO.
                RotateRobotPositive(1350);
                r.runTwoPCAMotor(14, 430, 15, -480, 2000);
                RotateRobotPositive(750);
                r.runTwoPCAMotor(14, 330, 15, -282, 1500);
                r.runPCAServo(8, 180);
                Conductivity();

                System.exit(0);
    }
    
    
    
    //robot rotation functions
    public static void RotateRobotPositive(int time) {
	    r.runTwoPCAMotor(14, 180, 15, 180, time); //rotates the robot 90 degrees when battery is at 13.0 V //1325 for 90
	    //motor strength 180, channels 0 and 1, time: .8 seconds
	}
	    
    public static void RotateRobotNegative(int time) {
	    r.runTwoPCAMotor(14, -80, 15, -80, time); //rotates the robot approximately -90 degrees when battery is at 13.0 V //850 for 90
	    //motor strength 150, channels 0 and 1, time: .8 seconds
	}
    
    public static void FindBeacon (char beaconChar) {
                char beaconInput = '0';
                char beaconOutput = beaconChar; //character sent out by beacon CHANGE THIS!!!
                for(;;) {
                            beaconInput = r.getIRChar();
                            System.out.println(beaconInput);
			if(beaconInput == beaconOutput) {
				RotateRobotNegative(200);
				//r.sleep(1000);
//				r.runTwoPCAMotor(14, -145, 15, 173, 200);//backwards
//				r.sleep(1000);
//				RotateRobotPositive(100);
				r.sleep(1000);
				
                            break;
			}
                r.sleep(200);
                RotateRobotNegative(100);
                r.sleep(1000);
                }
                }
        
    public static void BumpSensor (int pin) {
                DigitalPin temp = r.getDigitalPin(pin);
                while (temp.getValue() == 0) {
                    r.runTwoPCAMotor(14, 240, 15, -200, 100);
                    r.refreshDigitalPins();
                    temp = r.getDigitalPin(pin);
                    temp.getValue();
                }
    }
    public static void Conductivity () {
                r.refreshAnalogPins();
        double reading;
        reading = r.getConductivity();
        reading -= 1073;
        reading = reading/-42.7;
        System.out.printf("Soil moisture content percentage: %.2f\n", reading);
    }
}
