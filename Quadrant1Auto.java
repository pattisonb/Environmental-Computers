import rxtxrobot.*;

public class Quadrant1Auto {
    static RXTXRobot r = new ArduinoNano();
    
    public static void main(String[] args) {
        
		r.setPort("/dev/tty.usbmodem14301"); 
		r.connect();
                
                r.runTwoPCAMotor(14, 330, 15, -282, 3000);
                RotateRobotNegative(1200);
                r.runTwoPCAMotor(14, 235, 15, -200, 1200);
                FindBeacon('Y');
                r.runTwoPCAMotor(14, 430, 15, -480, 3000);
                r.sleep(200);
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
	    r.runTwoPCAMotor(14, -150, 15, -150, time); //rotates the robot approximately -90 degrees when battery is at 13.0 V //850 for 90
	    //motor strength 150, channels 0 and 1, time: .8 seconds
	}
    
    public static void FindBeacon (char beaconChar) {
                char beaconInput = '0';
                char beaconOutput = beaconChar; //character sent out by beacon CHANGE THIS!!!
                for(int i = 0; i <= 180; i+=10) {
                            beaconInput = r.getIRChar();
                            System.out.println(beaconInput);
			if(beaconInput == beaconOutput) {
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
