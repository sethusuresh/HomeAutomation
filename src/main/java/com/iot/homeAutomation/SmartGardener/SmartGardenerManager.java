package com.iot.homeAutomation.SmartGardener;

public interface SmartGardenerManager {

	String mapUserToDevice(String deviceId, String userId) throws Exception;

	boolean startWatering(String deviceId, String userId) throws Exception;

}
