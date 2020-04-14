package com.iot.homeAutomation.MQTT;

import javax.annotation.Resource;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQTTStreamManagerImpl implements MQTTStreamManager {

	@Resource
	IMqttAsyncClient mqttClient;
	
	@Autowired
	MQTTTopics mqttTopics;
	
	@Override
	public void publishMessage(String message, String topic) throws Exception {
		try {
			mqttClient.publish(topic, message.getBytes(), 2, true);
			System.out.println("Message published to topic:- " + topic);
		} catch (MqttException e) {
			System.out.println("Error publishing message to topic:- " + topic + " with error message:- " + e.getMessage() + " and error code:- " + e.getReasonCode());
			throw new Exception();
		}
	}

	@Override
	public void subscribe() throws MqttException, InterruptedException {
		IMqttMessageListener messageListener = new IMqttMessageListener() {
			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				System.out.println("Message received from MQTT for topic:- " + topic + " and Message:- " + message);
			}
		};
		mqttClient.subscribe(mqttTopics.getSmartGardenerSubTopic(), 2, messageListener);
		System.out.println("Subscribed to MQTT topic:- " + mqttTopics.getSmartGardenerSubTopic());
	}
}
