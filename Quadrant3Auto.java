//13.3V
import rxtxrobot.*;

public class Quadrant3Auto {
    static RXTXRobot r = new ArduinoNano();
    static AnalogPin temp;
    
    public static void main(String[] args) {
        
		r.setPort("COM3"); 
		r.connect();      
		
		int distance;
                int PING_PIN = 9;
                int INC_PIN = 1;
////                
                r.runTwoPCAMotor(14, -145, 15, 220, 1100); //move straight
//                //first task: navigate through movebale gaps
                
                //going through the first gap
                while (r.getPing(PING_PIN) <= 60) {
                    r.runTwoPCAMotor(14, -165, 15, 200, 200);//move forward for x amount of time
                }//find gap using ping sensor
//                r.runTwoPCAMotor(14, 235, 15, -235, 150);
//                r.sleep(200);
                RotateRobotNegative(350);//Turn left through the gap (350 at 13.3V, 390 at 13.0V)
                r.sleep(700);
                r.runTwoPCAMotor(14, 245, 15, -260, 540); //Go through the gap (620 at 13.5)
                r.sleep(2000);
                RotateRobotPositive(900); //turn right (900 at 13.3V)
                r.sleep(2000);
                r.runTwoPCAMotor(14, 275, 15, -275, 500); //go straight
               
                //Going through the second gap
                while (r.getPing(PING_PIN) <= 40) {
                    r.runTwoPCAMotor(14, 175, 15, -125, 200);//move forward for x amount of time
                }//find gap using ping sensor
                r.sleep(1000);
                RotateRobotNegative(885);//turn left (885 at 13.1V)
                r.runTwoPCAMotor(14, 235, 15, -235, 1750);//go through the gap
                r.sleep(2000);
                RotateRobotPositive(250);//turn right
                r.sleep(2000);
                r.runTwoPCAMotor(14, 235, 15, -235, 500);//go right for a little so that robot can turn and find beacon
                r.sleep(4000);
                FindBeacon('Y');//find beacon
                //r.runTwoPCAMotor(14, -174, 15, 125, 500);//go up the ramp
                r.runTwoPCAMotor(14, 174, 15, -125, 3000);//go up the ramp
                //r.runTwoPCAMotor(14, 350, 15, -250, 3000);
                r.sleep(8000);
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
                for(;;) {
                            beaconInput = r.getIRChar();
                            System.out.println(beaconInput);
			if(beaconInput == beaconOutput) {
                            break;
			}
                r.sleep(200);
                RotateRobotNegative(20);
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
        System.out.println(("The angle of incline is: " + ((temp.getValue() - 635.56) / 3.55)));
    }
}
