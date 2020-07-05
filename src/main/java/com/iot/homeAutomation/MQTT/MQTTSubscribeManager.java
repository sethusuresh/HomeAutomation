package com.iot.homeAutomation.MQTT;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttException;

public interface MQTTSubscribeManager {

	IMqttMessageListener subscribe() throws MqttException, InterruptedException;

}
