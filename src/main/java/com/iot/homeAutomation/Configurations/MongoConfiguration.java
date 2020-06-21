package com.iot.homeAutomation.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
public class MongoConfiguration {
	
	@Bean
    public MongoClient mongoClient() {
        ServerAddress serverAddress = new ServerAddress("ssautohome.hopto.org", 27017);
        MongoCredential credential = MongoCredential.createCredential("raspberrypi", "admin", "SS1994ekm@".toCharArray());
        MongoClientOptions options = MongoClientOptions.builder().minConnectionsPerHost(5).build();
        return new MongoClient(serverAddress, credential, options);
    }
 
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), "Home_Automation");
    }
}
