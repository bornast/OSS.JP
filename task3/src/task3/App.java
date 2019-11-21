package test.test;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

    	Gson gson = new Gson();
            	    	
        String measurerJson = "{\"sensors\":[{\"name\":\"sensor1\",\"factor\":10,\"minValue\":-32668,\"maxValue\":32667,\"unitOfMeasure\":\"C\"},{\"name\":\"sensor2\",\"factor\":10,\"minValue\":-32668,\"maxValue\":32667,\"unitOfMeasure\":\"C\"}]}";
                  
        WaterMeasurer measurer = gson.fromJson(measurerJson, WaterMeasurer.class);
        
		/*Sensor[] sensorsFromJson = gson.fromJson(measurerJson, Sensor[].class);
		List<Sensor> sensors = new ArrayList(Arrays.asList(sensorsFromJson));
		WaterMeasurer measurer = new WaterMeasurer(sensors);
		String test = gson.toJson(measurer);
		System.out.println(test);*/
		        
		try {
			measurer.StartPublishing();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int a = 5;
        
    	
    }
}
