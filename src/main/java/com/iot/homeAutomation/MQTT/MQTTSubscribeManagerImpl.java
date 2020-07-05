package com.iot.homeAutomation.MQTT;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MQTTSubscribeManagerImpl implements MQTTSubscribeManager {

	private static final Logger logger = LoggerFactory.getLogger(MQTTSubscribeManagerImpl.class);

	@Override
	public IMqttMessageListener subscribe() throws MqttException, InterruptedException {
		IMqttMessageListener messageListener = new IMqttMessageListener() {
			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				logger.debug("Message received from MQTT for topic:- {} and Message:- {}", topic, message);
			}
		};
		return messageListener;
	}
}
