package AAA.AAA;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class WaterMeasurer {
   
	private String topic;
	private String broker;
	private String clientId;
	private List<Sensor> sensors;
    
    public WaterMeasurer() {
    }
         
    public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
    
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
    public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}
    
    public WaterMeasurer(List<Sensor> sensors) {
        this.sensors = sensors;
    }     
    
    public List<Sensor> getSensors() {
		return sensors;
	}
    
    public void startSensors() {
		for (Sensor sensor : this.sensors) {
			sensor.start();
		}
	}
    
    public void StartPublishing() throws InterruptedException {
        	
        MemoryPersistence persistence = new MemoryPersistence();
        int timesToPublish = 0;
        
        try {
            MqttClient client = new MqttClient(this.broker, this.clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            client.connect(connOpts);
            
            while (timesToPublish < 30) {
	            for (int i = 0; i < sensors.size(); i++) { 
	            	MqttMessage sensorMessage = new MqttMessage(sensors.get(i).toString().getBytes());
	            	client.publish(this.topic + "/" + sensors.get(i).GetSensorName(), sensorMessage);
	            }
            
	            TimeUnit.SECONDS.sleep(1);
	            timesToPublish++;
            }
            client.disconnect();
            System.exit(0);
        } catch(MqttException me) {            
            me.printStackTrace();
        }    	
    }
}
	
	
	


