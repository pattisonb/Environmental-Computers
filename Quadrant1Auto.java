import rxtxrobot.*;

public class Quadrant1Auto {
    static RXTXRobot r = new ArduinoNano();
    
    public static void main(String[] args) {
        
		r.setPort("/dev/tty.usbmodem14301"); 
		r.connect();
                
                //first task: exit start box and cross bridge
                r.runTwoPCAMotor(14, 235, 15, -200, 4900); //moving robot three meters in this, adjust for length of bridge
                
                //second task, locate volcano and naviagate towards it
                
                int[] angles = {0, 0, 0}; //array of angles detected
                char beaconInput = '0';
                char beaconOutput = 'K'; //character sent out by beacon
                int avgAngle = 0;
                int rotationAngle = 0;
                int timeAngle = 0; //time based on rotaitonAngle
                
                for(int i = 30; i <= 180; i+=10) {
                    r.runPCAServo(0, i);
                    for (int j = 0; j < 3; j ++) { //try to get angle five times to avoid random ir signals
                            beaconInput = r.getIRChar(); //the code received from the beacon
			if(beaconOutput == beaconInput) {
                            angles[j] = i;
			}
                }
                            if (angles[0] == angles[1] && angles[1] == angles[2] && beaconInput != '0') { //if all the angles are the same it updates the avgAngle
                            avgAngle = i;
                                if (angles[0] == 0) {
                                    break;
                                }
                            }
                            
                }
                System.out.println(avgAngle);
                rotationAngle = 90 - avgAngle; //change rotation based on the way the robot is facing 
                r.runPCAServo(0, 90); //make the servo point forwards
                r.sleep(400);
                if (rotationAngle == 0) { //if the beacon is right in front of the robot it will just go forwards
                    r.runPCAServo(0, 90);
                    r.sleep(300);
                }
                if (rotationAngle > 0) {
                    timeAngle = ((2000)/(90/rotationAngle)); //change the time function adjusted on the angle
                    r.runPCAServo(0, rotationAngle); //point servo towards beacon
                    r.sleep(2000);
                    RotateRobotPositive(timeAngle);
                    r.sleep(2000);
                }
                if (rotationAngle < 0) {
                    rotationAngle = rotationAngle * -1; //this value will be negative so multiplied by -1
                    timeAngle = ((850)/(120/rotationAngle)); //dividing by 120 because of results needing more rotation
                    System.out.println(rotationAngle);
                    System.out.print(timeAngle);
                    r.sleep(2000);
                    RotateRobotNegative(timeAngle);
                    r.sleep(2000);
                }
                r.runPCAServo(0, 90);
                
                //third task: reach the summit of the volcano
                r.runTwoPCAMotor(14, 235, 15, -200, 4900); //moving robot three meters in this, adjust for distance to summit
                
                //fourth task: turn robot and navigate down volcano
                RotateRobotPositive(1325); //rotating robot 90º to the right
                r.runTwoPCAMotor(14, 235, 15, -200, 4900); //moving robot three meters in this, adjust for distance to the bottom of the volcano
                
                //fifth task: find surface water region
                //based on map on canvas, robot should rotate about 15º after volcano
                RotateRobotPositive(89); //rotating robot
                //moving forward for a little bit until next to water region
                r.runTwoPCAMotor(14, 235, 15, -200, 1000);
                RotateRobotPositive(1325); //rotating robot 90º to the right to face water region
                //move foward until at water region
                //could utilize bump sensors here but could also just drive until it hits container
                 r.runTwoPCAMotor(14, 235, 15, -200, 1000);
                 
                 //sixth task: deploy conductivty prode into surface water region
                 r.runPCAServo(1, 90); //put probe servo on channel 1 while navigation servos is on channel 0
                 
                 //seventh task: meausure conductivity

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
}
