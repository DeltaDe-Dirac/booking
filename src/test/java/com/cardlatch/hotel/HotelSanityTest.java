package com.cardlatch.hotel;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HotelSanityTest {

	@Autowired
	private HotelController controller;

	@Test
	void contextLoads() throws Exception {
		assertNotNull(controller);
	}

}
