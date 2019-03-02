import rxtxrobot.*;

public class GetPing
{

	public static void main(String[] args)
	{
		//we have 3 ping sensors
		//do we need to write codes for them seperately?
		int LEFT_PIN = 12;
		int RIGHT_PIN = 13;
		int CENTER_PIN = 14;
		RXTXRobot r = new ArduinoNano(); // Create RXTXRobot object
		r.setPort("COM3"); // Set the port to COM3
		r.connect();
		for (int x=0; x < 100; ++x)//run the ping sensors 100 times?
		{
			//Read the ping sensor value, which is connected to pin 12
			int leftDistance = r.getPing(LEFT_PIN);
			int rightDistance = r.getPing(RIGHT_PIN);
			int centerDistance = r.getPing(CENTER_PIN);
			//think about different scenarios 
			if(leftDistance < 10 && centerDistance < 10) { //if there are walls on the left side and front of the robot
				r.runTwoPCAMotor(0, 200, 1, -200, 5000);//turn right; need to change the time 5000  
			}
			if(rightDistance < 10 && centerDistance < 10) { //if there are walls on the right side and front of the robot
				r.runTwoPCAMotor(0, 200, 1, -200, 5000);//turn left 
			}
			if(centerDistance < 10){//if there is a wall in front of the robot
				r.runTwoPCAMotor(0, 200, 1, -200, 10000); //turn left until the robot turns 180 degrees around (or not? or we can make it turn left or right?) 
			}
			r.sleep(300);//what is this for?
		}
		r.close();
	}
}
