package oss.transaction.Bll.Services;

import com.google.common.base.Strings;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Service;
import oss.transaction.Bll.Dtos.MqttMessageDto;
import oss.transaction.Bll.Interfaces.IMqttService;

@Service
public class MqttService implements IMqttService {

    @Override
    public void publish(MqttMessageDto mqttMessage) throws Exception {
        try {
            MqttClient mqttClient = initializeMqttClient(mqttMessage);
            mqttClient.publish(mqttMessage.topic, new MqttMessage(mqttMessage.message.getBytes()));
            mqttClient.disconnect();
        } catch(MqttException me) {
            throw new Exception("Error establishing a MQTT connection");
        }
    }

    @Override
    public void validateMqttMessage(MqttMessageDto mqttMessage) throws Exception {
        String errorMessage = "";
        if (Strings.isNullOrEmpty(mqttMessage.broker)) errorMessage += "Field broker is required.";
        if (Strings.isNullOrEmpty(mqttMessage.clientId)) errorMessage += "Field clientId is required.";
        if (Strings.isNullOrEmpty(mqttMessage.topic))  errorMessage += "Field topic is required.";
        if (errorMessage.length() > 0)  throw new Exception(errorMessage);
    }

    private MqttClient initializeMqttClient(MqttMessageDto mqttMessage) throws MqttException {
        MemoryPersistence persistence = new MemoryPersistence();
        MqttClient mqttClient = new MqttClient(mqttMessage.broker, mqttMessage.clientId, persistence);
        MqttConnectOptions connectionOptions = new MqttConnectOptions();
        connectionOptions.setCleanSession(true);
        mqttClient.connect(connectionOptions);
        return mqttClient;
    }
}
