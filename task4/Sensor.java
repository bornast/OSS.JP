package AAA.AAA;
import java.util.Random;

public class Sensor extends Thread {
// public class Sensor {
    
    private String sensorName;
    private int factor;
    private int minValue;
    private int maxValue;
    private IUnitOfMeasure unitOfMeasure;
    private String generatedValue;

    public Sensor() {
    }

    public Sensor(String sensorName, int minValue, int maxValue, int factor, IUnitOfMeasure unitOfMeasure) {
        this.sensorName = sensorName;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.factor = factor;
        this.unitOfMeasure = unitOfMeasure;
    }

    public String GetSensorName() {
        return this.sensorName;
    }
    
    public String GetRandomMessage() {
    	if (this.factor == 0)
    		factor = 1;
        int randomValue = getRandomNumberInRange(minValue, maxValue);
        float sensorValue = randomValue/factor;		
        return Float.toString(sensorValue) + " " + unitOfMeasure.toString();
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    
    @Override
	public String toString() {
		return this.generatedValue;
	}
    
    @Override
    public void run() {
    	while (true) {
			this.generatedValue = this.GetRandomMessage();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
	}
    
}