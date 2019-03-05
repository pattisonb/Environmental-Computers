import rxtxrobot.*;

public class Servo {
    public static void main(String[] args)
	{
		RXTXRobot r = new ArduinoNano(); // Create RXTXRobot object
		r.setPort("/dev/tty.usbmodem14101"); // Set the port to COM3
		r.setVerbose(true); // Turn on debugging messages
		r.connect();
                
                r.runPCATimedServo(0, 33, 1000);
                r.sleep(1000);
                r.runPCATimedServo(0, 90, 1000);
                r.sleep(1000);  
                r.runPCATimedServo(0, 180, 1000);
                
                //r.runPCATimedServo(0, 0, 1000);
                
                r.sleep(2000);
                
//                for(int i = 0; i <= 180; i+=5) {
//			r.runPCAServo(0, i);
//                        r.sleep(200);
//                        System.out.println(i);
//                }
//                r.runPCATimedServo(0, 90, 1000);
//                
               System.exit(0);
//        }
}
