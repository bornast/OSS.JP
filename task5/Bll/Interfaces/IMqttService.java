package oss.transaction.Bll.Interfaces;

import oss.transaction.Bll.Dtos.MqttMessageDto;

public interface IMqttService {
    public void publish(MqttMessageDto mqttMessage) throws Exception;
    public void validateMqttMessage(MqttMessageDto mqttMessage) throws Exception;
}
