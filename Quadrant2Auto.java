import rxtxrobot.*;

public class Quadrant2Auto {
    static RXTXRobot r = new ArduinoNano();
    
    public static void main(String[] args) {
        
		r.setPort("/dev/tty.usbmodem14301"); 
		r.connect();
                
                //first task: naviagte to swamp
                //unsure how to code for bump sensor so pseudocode in place of real code
                //while(not bumping into wall)
                    r.runTwoPCAMotor(14, 235, 15, -200, 1000);//move forward for x amount of time
                RotateRobotPositive(1325);//after bumping into first wall rotate robot 90º to the right
                //while(not bumping into wall)
                    r.runTwoPCAMotor(14, 235, 15, -200, 1000);//move forward for x amount of time
                    
                //second task: deploy scoop
                //unsure how this is going to work so a simple servo rotation function included
                r.runPCAServo(0, 90); //not sure what angle we will have to set it to
                
                //third task: retrieve salamanders
                //again, unsure how this is going to work so a simple servo rotation function included
                r.runPCAServo(0, 90); //not sure what angle we will have to set it to
                
                //fourth task naviagte to return zone
                //gonna use a ping sensor here since the bump sensor will be blocked my scoop
                int PING_PIN = 12;
                int distance = 0;
                distance = r.getPing(PING_PIN);
                while (distance > 25) {
                    r.runTwoPCAMotor(14, 235, 15, -200, 500);//move forward for x amount of time
                    distance = r.getPing(PING_PIN);
                }
                //rotate robot to the left
                RotateRobotNegative(850); //rotating 90º
                //then drive until at return zone so maybe once within ten cm?
                while (distance > 10) {
                    r.runTwoPCAMotor(14, 235, 15, -200, 500);//move forward for x amount of time
                    distance = r.getPing(PING_PIN);
                }
                
                //fifth task: deliver salamanders
                r.runPCAServo(0, 90); //not sure what angle we will have to set it to
                
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
}
    