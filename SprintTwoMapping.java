import rxtxrobot.*;
public class SprintTwoMapping {
	static RXTXRobot r = new ArduinoNano();
	
	public static void main(String[] args) {
		r.setPort("/dev/tty.usbmodem14501");
		r.connect();
		
		int LEFT_PIN = 12; //Analog pin of our left Ping Sensor
		
		for (int i = 0; i < 10; i++) {
			r.runTwoPCAMotor(0, -200, 1, 200, 1000); // change time/motor strength to change length robot moves
			if (r.getPing(LEFT_PIN) < 10) { //prints X if it detects cinderblock within 10 cm.
				System.out.print("X");
			}
			else { //prints space if nothing is detected within 10 cm.
				System.out.print(" ");
			r.sleep(2000); //rest for 2 seconds
			}
		}
	}

}
