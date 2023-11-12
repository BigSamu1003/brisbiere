package com.brisbiere.Brisbiere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class BrisbiereApplication {
	private final static Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public static void main(String[] args) {
		SpringApplication.run(BrisbiereApplication.class, args);
		LOGGER.log(Level.INFO, "SERVER RUNNING IN PORT:8090");
	}

}
