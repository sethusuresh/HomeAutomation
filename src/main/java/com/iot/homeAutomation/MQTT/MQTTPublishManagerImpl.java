package com.iot.homeAutomation.MQTT;

import javax.annotation.Resource;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MQTTPublishManagerImpl implements MQTTPublishManager {

	private static final Logger logger = LoggerFactory.getLogger(MQTTPublishManagerImpl.class);
	
	@Resource
	IMqttAsyncClient mqttClient;
	
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

}
