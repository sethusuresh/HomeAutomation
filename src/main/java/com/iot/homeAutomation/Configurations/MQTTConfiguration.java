package com.iot.homeAutomation.Configurations;

import javax.annotation.Resource;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.iot.homeAutomation.MQTT.MQTTSubscribeManager;
import com.iot.homeAutomation.MQTT.MQTTTopics;

@Configuration
public class MQTTConfiguration {
	
	private static final Logger logger = LoggerFactory.getLogger(MQTTConfiguration.class);
	
	@Resource
	MQTTSubscribeManager mqttSubscribeManager;
	
	@Autowired
	MQTTTopics mqttTopics;

	@Bean
	public IMqttAsyncClient mqttClient() throws MqttException, InterruptedException {
		IMqttAsyncClient client = new MqttAsyncClient(getServerURI() , "HOME_AUTOMATION", new MemoryPersistence());
		try {
			logger.debug("Connecting to broker:- {}", getServerURI());
			client.connect(getOptions()).waitForCompletion();
			if(client.isConnected()) {
				logger.debug("Connected to broker:- {}", getServerURI());
				client.subscribe(mqttTopics.getSmartGardenerSubTopic(), 2, mqttSubscribeManager.subscribe());
				logger.debug("Subscribed to MQTT topic:- {}", mqttTopics.getSmartGardenerSubTopic());
			}
		} catch (Exception e) {
			logger.error("Error establising connecting to MQTT broker:- {}", e);
		}
		return client;
	}
	
	private String getServerURI() {
		return "tcp://ssautohome.hopto.org:1883";
	}
	
	private MqttConnectOptions getOptions() {
		MqttConnectOptions options = new MqttConnectOptions();
		options.setUserName("raspberrypi");
		options.setPassword("SS1994ekm@".toCharArray());
		options.setAutomaticReconnect(true);
		options.setCleanSession(false);
		options.setConnectionTimeout(0);
		return options;
	}
}
