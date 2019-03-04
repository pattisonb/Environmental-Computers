import rxtxrobot.*;

public class CinderblockTest {
    public static void main(String[] args)
	{
            int PING_PIN = 5;
            RXTXRobot r = new ArduinoNano(); // Create RXTXRobot object
            r.setPort("/dev/tty.usbmodem14101"); // Set the port to correct port
            r.connect();
            
            for (int i = 0; i < 40; i++) {  //don't know how long to iterate yet because we don't know how long the course will be
                //currently set to run for ten feet if runDCmotor call is accurate to 3 inches
                if (r.getPing(PING_PIN) <= 25) { //ping after moving or before?
                        System.out.print("X");
                    }
                else {
                        System.out.print("O");
                    }
                r.runTwoPCAMotor(0, -100, 1, 100, 100); //calibrate to move robot about 3 inches after pinging
            }
                
        }
    
}
