package com.cardlatch.hotel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelApplication {
	private static final Logger LOG = LoggerFactory.getLogger(HotelApplication.class);

	public static void main(String[] args) {
		if (args.length == 0) {
			LOG.warn("Missing argument 'jasypt.encryptor.password' for properties decryption");
		} else {
			System.setProperty("jasypt.encryptor.password", args[0]);
		}

		SpringApplication.run(HotelApplication.class, args);
	}

}
