package com.iot.homeAutomation.MQTT;

import org.springframework.stereotype.Service;

@Service
public class MQTTTopics {

	private String smartGardenerPubTopic = "homeautomation/smartgardener/{deviceId}";
	private String smartGardenerSubTopic = "homeautomation/smartgardener";
	
	public String getSmartGardenerPubTopic(String deviceId) {
		return smartGardenerPubTopic.replace("{deviceId}", deviceId);
	}
	public String getSmartGardenerSubTopic() {
		return smartGardenerSubTopic;
	}
	
}
