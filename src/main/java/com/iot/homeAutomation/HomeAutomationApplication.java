package com.iot.homeAutomation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HomeAutomationApplication {

	private static final Logger logger = LoggerFactory.getLogger(HomeAutomationApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(HomeAutomationApplication.class, args);
	}
	
	@RequestMapping(value = "/keepalive", method = RequestMethod.GET)
	public String health() {
		logger.debug("keepalive");
		return "OK 2";
	}

}
