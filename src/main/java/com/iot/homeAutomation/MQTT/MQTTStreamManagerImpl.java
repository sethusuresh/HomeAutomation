package com.iot.homeAutomation.MQTT;

import javax.annotation.Resource;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQTTStreamManagerImpl implements MQTTStreamManager {

	private static final Logger logger = LoggerFactory.getLogger(MQTTStreamManagerImpl.class);
	@Resource
	IMqttAsyncClient mqttClient;
	
	@Autowired
	MQTTTopics mqttTopics;
	
	@Override
	public void publishMessage(String message, String topic) throws Exception {
		try {
			mqttClient.publish(topic, message.getBytes(), 2, true);
			logger.debug("Message published to topic:- {}", topic);
		} catch (MqttException e) {
			logger.debug("Error publishing message to topic:- {} with error message:- {} and error code:- {}", topic, e.getMessage(), e.getReasonCode());
			throw new Exception();
		}
	}

	@Override
	public void subscribe() throws MqttException, InterruptedException {
		IMqttMessageListener messageListener = new IMqttMessageListener() {
			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				logger.debug("Message received from MQTT for topic:- {} and Message:- {}", topic, message);
			}
		};
		mqttClient.subscribe(mqttTopics.getSmartGardenerSubTopic(), 2, messageListener);
		logger.debug("Subscribed to MQTT topic:- {}", mqttTopics.getSmartGardenerSubTopic());
	}
}
