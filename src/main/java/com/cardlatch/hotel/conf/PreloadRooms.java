package com.cardlatch.hotel.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cardlatch.hotel.entities.Room;
import com.cardlatch.hotel.repos.RoomRepository;

@Configuration
class PreloadRooms {

	private static final Logger LOG = LoggerFactory.getLogger(PreloadRooms.class);

	@Bean
	CommandLineRunner initRooms(RoomRepository repository) {
		repository.deleteAll();
		return args -> {
			LOG.info(repository.save(new Room(1, 2)).toString());
			LOG.info(repository.save(new Room(2, 2)).toString());
			LOG.info(repository.save(new Room(3, 3)).toString());
			LOG.info(repository.save(new Room(4, 5)).toString());
			LOG.info(repository.save(new Room(5, 5)).toString());
		};
	}
}