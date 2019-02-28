import rxtxrobot.*;

public class TemperatureProbe {
    public static RXTXRobot robot;
    
    public static void main(String[] args) {
        robot = new ArduinoUno();
        robot.setPort("/dev/tty.usbmodem14301");
        robot.connect();
        
        int thermistorReading = getThermistorReading();
        
        System.out.println("The probe read the value: " + thermistorReading);
        System.out.println("In volts: " + (thermistorReading * (5.0/1023.0)));
        double celsius = (thermistorReading - 677.7186471)/-6.93063423; 
        System.out.printf("In Degrees Celsius: %.2f\n", celsius);
    }  
    
    public static int getThermistorReading() {
        int sum = 0;
        int readingCount = 10;
        
        for (int i = 0; i < readingCount; i++) {
            robot.refreshAnalogPins();
            int reading = robot.getAnalogPin(0).getValue();
            sum += reading;
        }
        return sum / readingCount;
    }
    
    
    
    
}
