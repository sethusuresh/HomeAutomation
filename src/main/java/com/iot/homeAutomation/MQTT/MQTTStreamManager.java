package com.iot.homeAutomation.MQTT;

import org.eclipse.paho.client.mqttv3.MqttException;

public interface MQTTStreamManager {

	void publishMessage(String message, String topic) throws Exception;

	void subscribe() throws MqttException, InterruptedException;

}
