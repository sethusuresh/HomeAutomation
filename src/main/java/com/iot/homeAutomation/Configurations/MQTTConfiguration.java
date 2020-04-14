package com.iot.homeAutomation.Configurations;

import javax.annotation.Resource;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.iot.homeAutomation.MQTT.MQTTStreamManager;

@Configuration
public class MQTTConfiguration {
	
	@Resource
	MQTTStreamManager mqttStreamManager;

	@Bean
	public IMqttAsyncClient mqttClient() throws MqttException, InterruptedException {
		IMqttAsyncClient client = new MqttAsyncClient(getServerURI() , "HOME_AUTOMATION_BACKEND", new MemoryPersistence());
		System.out.println("Connecting to broker:- " + getServerURI());
		client.connect(getOptions()).waitForCompletion();
		if(client.isConnected()) {
			System.out.println("Connected to broker:- " + getServerURI());
		}
		return client;
	}
	
	@Bean
	@DependsOn({"mqttClient"})
	public void subscribe() {
		try {
			if(mqttClient().isConnected()) {
				mqttStreamManager.subscribe();
			}else {
				System.out.println("Unable to subscribe to MQTT topics since the client is not connected to the server");
			}
		} catch (MqttException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private String getServerURI() {
		return "tcp://ssautohome.hopto.org:1883";
	}
	
	private MqttConnectOptions getOptions() {
		MqttConnectOptions options = new MqttConnectOptions();
		options.setUserName("raspberrypi");
		options.setPassword("SS1994ekm@".toCharArray());
		options.setAutomaticReconnect(true);
		options.setCleanSession(true);
		options.setConnectionTimeout(10);
		return options;
	}
}
