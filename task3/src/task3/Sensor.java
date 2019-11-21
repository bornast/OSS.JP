package test.test;
import java.util.Random;

public class Sensor {
    
    private String name;
    private int factor;
    private int minValue;
    private int maxValue;
    private IUnitOfMeasure unitOfMeasure;

    public Sensor() {
    }

    public Sensor(String name, int minValue, int maxValue, int factor, IUnitOfMeasure unitOfMeasure) {
        this.name = name;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.factor = factor;
        this.unitOfMeasure = unitOfMeasure;
    }

    public String GetName() {
        return this.name;
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
}
