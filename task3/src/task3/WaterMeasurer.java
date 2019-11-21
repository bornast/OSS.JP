package test.test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class WaterMeasurer {
   
	private List<Sensor> sensors;
    
    public WaterMeasurer() {        
        /*sensors.add(SensorFactory.GetSensor("waterTemperature", IUnitOfMeasure.C));
        sensors.add(SensorFactory.GetSensor("waterPressure", IUnitOfMeasure.Bar));
        sensors.add(SensorFactory.GetSensor("waterConsuptionDay", IUnitOfMeasure.liter));
        sensors.add(SensorFactory.GetSensor("waterConsuptionYear", IUnitOfMeasure.m3));*/
    }
                
    public WaterMeasurer(List<Sensor> sensors) {
        this.sensors = sensors;
    }     
    
    public void StartPublishing() throws InterruptedException {

        String broker       = "tcp://127.0.0.1:1883";
        String clientId     = "JavaSample";
        MemoryPersistence persistence = new MemoryPersistence();
        int timesToPublish = 0;
        
	        try {
	            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
	            MqttConnectOptions connOpts = new MqttConnectOptions();
	            connOpts.setCleanSession(true);
	            sampleClient.connect(connOpts);
	            
	            while (timesToPublish < 3) {
	            
		            for (int i = 0; i < sensors.size(); i++) { 
		            
		            	MqttMessage sensorMessage = new MqttMessage(sensors.get(i).GetRandomMessage().getBytes());
		            	sampleClient.publish(sensors.get(i).GetName(), sensorMessage);
		            	
		            }	
		            TimeUnit.SECONDS.sleep(3);
		            timesToPublish++;
	            }
	            // dissconect
	            sampleClient.disconnect();
	            System.exit(0);
	        } catch(MqttException me) {            
	            me.printStackTrace();
	        }    	
    }
    
    
    	
}