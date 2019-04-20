import rxtxrobot.*;

public class Quadrant3Auto {
    static RXTXRobot r = new ArduinoNano();
    static AnalogPin temp;
    
    public static void main(String[] args) {
        
		r.setPort("/dev/tty.usbmodem14301"); 
		r.connect();
                int distance;
                int PING_PIN = 9;
                int INC_PIN = 1;
////                
                r.runTwoPCAMotor(14, -165, 15, 185, 1100);
//                //first task: navigate through movebale gaps
                while (r.getPing(PING_PIN) <= 60) {
                    r.runTwoPCAMotor(14, -165, 15, 200, 200);//move forward for x amount of time
                }
//                r.runTwoPCAMotor(14, 235, 15, -235, 150);
//                r.sleep(200);
                RotateRobotNegative(875);
                r.sleep(700);
                r.runTwoPCAMotor(14, 235, 15, -235, 1300);
                r.sleep(700);
                RotateRobotPositive(1350);
                r.sleep(500);
                while (r.getPing(PING_PIN) <= 40) {
                    r.runTwoPCAMotor(14, 175, 15, -125, 200);//move forward for x amount of time
                }
                RotateRobotNegative(900);
                r.runTwoPCAMotor(14, 235, 15, -235, 1500);
                FindBeacon('Y');
                r.runTwoPCAMotor(14, 174, 15, -125, 3000);
                r.runTwoPCAMotor(14, 350, 15, -250, 3000);
                Inclinometer(1);
                 
r.close();
    }
    
    public static void RotateRobotPositive(int time) {
	    r.runTwoPCAMotor(14, 180, 15, 180, time); //rotates the robot 90 degrees when battery is at 13.0 V //1325 for 90
	    //motor strength 180, channels 0 and 1, time: .8 seconds
            r.sleep(200);
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

    public static void Inclinometer(int ANALOG_PIN) {
        double sum = 0;
        double average = 0;
        for (int i = 0; i < 5; i++) {
            r.refreshAnalogPins();
            temp = r.getAnalogPin(ANALOG_PIN);
            sum += temp.getValue();
        }
        average = sum / 5;
        System.out.println(("The angle of incline is: " + (temp.getValue() - 626) / 3.55));
    }
}
