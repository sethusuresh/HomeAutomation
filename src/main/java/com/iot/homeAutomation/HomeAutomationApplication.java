package com.iot.homeAutomation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HomeAutomationApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeAutomationApplication.class, args);
		System.out.println("Welcome to Home Automation Project");
	}
	
	//testing
	@RequestMapping(value = "/keepalive", method = RequestMethod.GET)
	public String health() {
		return "OK";
	}

}
