import rxtxrobot.*;

public class CorrectServoMovement {
    public static void main(String[] args)
	{
		RXTXRobot r = new ArduinoNano();
		r.setPort("/dev/tty.usbmodem14301");
		r.setVerbose(true); // Turn on debugging messages
		r.connect();
                int[] angles = {0, 0, 0, 0, 0}; //array of angles detected
                char beaconInput = '0';
                char beaconOutput = 'K'; //character sent out by beacon
                int avgAngle = 0;
                
                for(int i = 30; i <= 180; i+=10) {
                    r.runPCAServo(0, i);
                    for (int j = 0; j < 5; j ++) { //try to get angle five times to avoid random ir signals
                            beaconInput = r.getIRChar(); //the code received from the beacon
			if(beaconOutput == beaconInput) {
                            angles[j] = i;
			}
                }
                            if (angles[0] == angles[1] && angles[1] == angles[2] && beaconInput != '0') {
                            avgAngle = i;
                                if (angles[0] == 0) {
                                    break;
                                }
                            }
                            
                }
                System.out.println(avgAngle);
                r.runPCAServo(0, avgAngle);
                System.exit(0);
        }
}
