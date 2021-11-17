package com.cardlatch.hotel.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cardlatch.hotel.entities.Guest;
import com.cardlatch.hotel.repos.GuestRepository;

@Configuration
class PreloadGuests {

	private static final Logger LOG = LoggerFactory.getLogger(PreloadGuests.class);

	@Bean
	CommandLineRunner initGuests(GuestRepository repository) {
		repository.deleteAll();
		return args -> {
			LOG.info(repository.save(new Guest("Guest1-1", 1)).toString());
			LOG.info(repository.save(new Guest("Guest1-2", 1)).toString());
			LOG.info(repository.save(new Guest("Guest2", 2)).toString());
			LOG.info(repository.save(new Guest("Guest3-1", 3)).toString());
			LOG.info(repository.save(new Guest("Guest3-2", 3)).toString());
			LOG.info(repository.save(new Guest("Guest3-3", 3)).toString());
			LOG.info(repository.save(new Guest("Guest4", 4)).toString());
		};
	}
}