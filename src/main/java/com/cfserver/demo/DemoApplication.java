package com.cfserver.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.ConfigurableApplicationContext;



@SpringBootApplication
@EnableConfigServer
public class DemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext cxt = SpringApplication.run(DemoApplication.class, args);
		logger.info("Running on port: " + cxt.getEnvironment().getProperty("local.server.port"));
	}

}
