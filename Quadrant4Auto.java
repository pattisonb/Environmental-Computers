import rxtxrobot.*;

public class Quadrant4Auto {
    static RXTXRobot r = new ArduinoNano();
    
    public static void main(String[] args) {
                r = new ArduinoUno();
		r.setPort("/dev/tty.usbmodem14301"); 
		r.connect();
                r.runPCAServo(8, 90);
                r.sleep(200);
                r.runTwoPCAMotor(14, 235, 15, -200, 1000);
                r.sleep(200);
                FindBeaconNeg('K');
                r.sleep(200);
                r.runTwoPCAMotor(14, 235, 15, -200, 1000);
                r.sleep(200);
                r.runPCAServo(8, 150);
                r.sleep(3000);
                double celsius = (getThermistorReading() - 677.7186471)/-6.93063423; 
                System.out.printf("In Degrees Celsius: %.2f\n", celsius);
                r.runPCAServo(8, 90);
                r.sleep(200);
                r.runTwoPCAMotor(14, -235, 15, 200, 200);
                r.sleep(200);
                RotateRobotNegative(825);
                r.sleep(200);
                r.runTwoPCAMotor(14, 235, 15, -200, 1000);
                r.sleep(200);
                RotateRobotPositive(1300);
                r.sleep(200);
                r.runTwoPCAMotor(14, 235, 15, -200, 600);
                r.sleep(200);
                FindBeaconPos('N');
                RotateRobotPositive(100);
                r.runTwoPCAMotor(14, 235, 15, -200, 2800);
                RotateRobotPositive(1500);
                r.runTwoPCAMotor(14, 235, 15, -200, 2000);
                
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
                public static void FindBeaconPos (char beaconChar) {
                    r.refreshDigitalPins();
                char beaconInput = '0';
                char beaconOutput = beaconChar; //character sent out by beacon CHANGE THIS!!!
                for(int i = 0; i <= 180; i+=10) {
                            beaconInput = r.getIRChar();
                            System.out.println(beaconInput);
			if(beaconInput == beaconOutput) {
                            break;
			}
                r.sleep(200);
                RotateRobotPositive(100);
                r.sleep(1000);
                }
                }
}
