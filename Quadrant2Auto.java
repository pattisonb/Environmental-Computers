import rxtxrobot.*;

public class Quadrant2Auto {
    static RXTXRobot r = new ArduinoNano();
    
    public static void main(String[] args) {
        
		r.setPort("COM3"); 
		r.connect();
                 r.runPCAServo(9, 45);//lift up the arm
                   r.sleep(300);
                
                //FIRST MOVE GO STRAIGHT
                r.runTwoPCAMotor(14, 200, 15, -150, 3100);//go straight
                r.sleep(1500);
                RotateRobotPositive(180);//turn right
                r.sleep(1500);
                r.runTwoPCAMotor(14, 220, 15, -160, 100);//go forward a little bit
                r.sleep(2000);
                FindBeacon('S');//try to find s
                r.sleep(2000);
                
                //GO TO THE RESCUING ZONE (4 senarios)
                //MIGHT NEED TO DECREASE MOTOR SPEED
                //at 13.3V - 13.5V
                
                //1
                /*RotateRobotPositive(60);//turn right
                r.sleep(750);
                r.runTwoPCAMotor(14, 215, 15, -170, 1400);//go to the rescuing zone
                */
                
                //2
                /*RotateRobotPositive(55);//turn right
                r.sleep(750);
                r.runTwoPCAMotor(14, 230, 15, -190, 1400);//go to the rescuing zone            
                */
                
                //3
                RotateRobotPositive(85);//turn right
                r.sleep(750);
                r.runTwoPCAMotor(14, 215, 15, -175, 1400);//go to the rescuing zone            
                
                
                //4
                /*RotateRobotPositive(120);//turn right
                r.sleep(750);
                r.runTwoPCAMotor(14, 215, 15, -175, 1600);//go to the rescuing zone            
*/
                
                
                
                
                r.sleep(200);
                r.runTwoPCAMotor(14, 225, 15, -200, 700);
                r.sleep(1000);
                //At the rescuing zone
                r.runPCAServo(9, 180);//close the arm (secure the salamanders)                
                r.sleep(1000);

                //GO BACKWARDS: MIGHT NEED TO INCREASE OR DECREASE TIME
                r.runTwoPCAMotor(14, -235, 15, 235, 2400);//go backwards (2200 at 13.5V)
                r.sleep(2000);
                
                //ROTATE TO G: MIGHT NEED TO INCREASE OR DECREASE TURN ANGLE
                RotateRobotPositive(230);//rotate and go forward so that it's easier to find g
                r.sleep(2000);
                
                r.runTwoPCAMotor(14, 235, 15, -235, 100);
                r.sleep(2000);
                FindBeacon('G');//find g
                //RotateRobotPositive(415);
                r.runTwoPCAMotor(14, 245, 15, -180, 900);
                r.sleep(1500);
                r.runTwoPCAMotor(14, 245, 15, -190, 500);
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
    			int i = 0;
                char beaconInput = '0';
                char beaconOutput = beaconChar; //character sent out by beacon CHANGE THIS!!!
                for(;;) {
                            beaconInput = r.getIRChar();
                            System.out.println(beaconInput);
                          	if(beaconInput == beaconOutput) {
                          		/*if(i <= 4) {
                          			 RotateRobotPositive(20);//turn right a little bit
                          		}*/
                          		break;
			}
                r.sleep(200);
                r.runTwoPCAMotor(14, 140, 15, 140, 130);
                r.sleep(1000);
                i++;
                }
                }
}
