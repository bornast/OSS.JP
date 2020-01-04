package AAA.AAA;

import com.google.gson.Gson;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Subscriber extends JFrame implements MqttCallback {

	private JFrame mainFrame;
	private Map<JLabel, JLabel> labels = new HashMap<JLabel, JLabel>();
	private Map<String, String> incomingData = new HashMap<String, String>();
	private WaterMeasurer measurer;
	private static Subscriber subscriber;
	private MqttClient client;
	private static boolean start;
	
	public void displayFrame() {
		mainFrame = new JFrame();
		mainFrame.setSize(1000, 1000);
		mainFrame.setLayout(new GridLayout(this.measurer.getSensors().size(), 2));

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		for (Sensor s : this.measurer.getSensors()) {
			this.labels.put(new JLabel(s.GetSensorName()), new JLabel("0"));
			this.incomingData.put(s.GetSensorName(), "0");
		}

		for (Map.Entry<JLabel, JLabel> entry : this.labels.entrySet()) {
			this.mainFrame.add(entry.getKey());
			this.mainFrame.add(entry.getValue());
		}
		mainFrame.setVisible(true);
	}
	
	public void getConnOptions() {
		
		String filePath = "json.json";
	    String measurerJson = "";
	    try {
	    	measurerJson = new String ( Files.readAllBytes( Paths.get(filePath) ) );
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	    
	    Gson gson = new Gson();
	    
	    this.measurer = gson.fromJson(measurerJson, WaterMeasurer.class);
	}
	
	public void connect() {
		try {
			this.client = new MqttClient(this.measurer.getBroker(), "subscriber", new MemoryPersistence());
			this.client.setCallback(this);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			this.client.connect(connOpts);
			this.client.subscribe(this.measurer.getTopic()+"/#", 0);
		} catch (MqttSecurityException e) {
			e.printStackTrace();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public void messageArrived(String topic, MqttMessage message) throws Exception {		
		String sensorTopic = topic.substring(topic.indexOf('/') + 1);
		String[] msgData = new String(message.getPayload()).split(":");
		this.incomingData.put(sensorTopic, message.toString());
	}
	
	public void refresh() {
		for (Map.Entry<JLabel, JLabel> entry : this.labels.entrySet()) {
			String labelName = entry.getKey().getText();
			entry.getValue().setText(this.incomingData.get(labelName));
		}
	}
	
	public static void main(String args[]) throws InterruptedException {
		subscriber = new Subscriber();
		subscriber.getConnOptions();
		subscriber.connect();

		while (true) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {					
					if (!start) {
						subscriber.displayFrame();
						start = true;
					}
					subscriber.refresh();
				}
			});
			Thread.sleep(1000);			
		}
	}

	public void connectionLost(Throwable cause) {}

	public void deliveryComplete(IMqttDeliveryToken token) {}
	
}
