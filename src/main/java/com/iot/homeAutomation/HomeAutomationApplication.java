package com.iot.homeAutomation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
		logger.debug("keepalive is working");
		return "OK";
	}
	
	@Configuration
	public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests().anyRequest().permitAll()  
	            .and().csrf().disable();
	    }
	}

}
