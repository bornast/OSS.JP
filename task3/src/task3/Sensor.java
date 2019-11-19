
package task3;
import java.util.Random;

public class Sensor {
    
    private String name;

    public Sensor() {

    }

    public Sensor(String name) {
        this.name = name;
    }

    public String GetName() {
        return this.name;
    }

    public void SetRandomName(int minValue, int maxValue, int factor, IUnitOfMeasure unit) {
        int randomValue = getRandomNumberInRange(minValue, maxValue);
        float sensorValue = randomValue/factor;		
        this.name = Float.toString(sensorValue) + " " + unit.toString();
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }	
}
