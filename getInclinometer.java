import rxtxrobot.*;

public class getInclinometer
{   
    public static void main(String[] args)
    {    
    	int ANALOG_PIN = 1;
	    // All sensor data will be read from the analog pins
		
	    RXTXRobot r = new ArduinoNano(); //Create RXTXRobot object

		r.setPort("COM3"); // Sets the port to COM5
		
		r.connect();

		r.refreshAnalogPins(); // Cache the Analog pin information

			AnalogPin temp = r.getAnalogPin(ANALOG_PIN);
			System.out.println("Sensor " + ANALOG_PIN + " has value: " + ((temp.getValue()-606)/ 3.877777));
    }
}