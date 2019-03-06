import rxtxrobot.*;

public class CinderblockTest {
    public static void main(String[] args)
	{
            int PING_PIN = 5;
            int distance = 0;
            int motorLeft = 14;
            int motorRight = 15;

            RXTXRobot r = new ArduinoNano(); // Create RXTXRobot object
            r.setPort("/dev/tty.usbmodem14301"); // Set the port to correct port
            r.connect();
            
            for (int i = 0; i < 10; i++) {  //don't know how long to iterate yet because we don't know how long the course will be
                //currently set to run for ten feet if runDCmotor call is accurate to 3 inches
                distance = r.getPing(PING_PIN);
                System.out.println(distance);
                if (distance <= 25) { //ping after moving or before?
                        System.out.print("X");
                    }
                else if (distance >= 25){
                        System.out.print("O");
                    }
                r.sleep(300);
                r.runTwoPCAMotor(motorRight, -100, motorLeft, 175, 50); //calibrate to move robot about 3 inches after pinging
                r.sleep(500);
            }
            System.exit(0);
                
        }
    
}
