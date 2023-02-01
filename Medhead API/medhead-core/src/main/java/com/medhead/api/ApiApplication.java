package com.medhead.api;

import com.medhead.api.services.EmergencyServiceImpl;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ApiApplication
{
	private static Logger logger = LoggerFactory.getLogger(ApiApplication.class);
	public static void main(String[] args)
	{
		SpringApplication.run(ApiApplication.class, args);
		logger.info("Application started.");
	}

	@Bean
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}

}
