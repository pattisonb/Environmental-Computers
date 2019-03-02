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
			System.out.println("Response: " + leftDistance + " cm");
			//think about different scenarios 
			if(leftDistance < 10) { //if distance is smaller than 10cm (or smaller/larger?)
				//stop the robot and turn it 
			}
			r.sleep(300);//what is this for?
		}
		r.close();
	}
}
