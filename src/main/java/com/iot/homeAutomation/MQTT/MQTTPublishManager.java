package com.iot.homeAutomation.MQTT;

public interface MQTTPublishManager {

	void publishMessage(String message, String topic) throws Exception;

}
