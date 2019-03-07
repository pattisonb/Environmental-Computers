import rxtxrobot.*;

public class Move3M {
    static RXTXRobot r = new ArduinoNano();
    
    public static void main(String[] args) {
        
		r.setPort("/dev/tty.usbmodem14301"); 
		r.connect();
                
                //move 3m
		//r.runTwoPCAMotor(14, -282, 15, 400, 2000);
                r.runTwoPCAMotor(15, -200, 14, 260, 4700);
                //15 rught 14 left
                System.exit(0);
    }
}
    
    
