package com.cardlatch.hotel;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.cardlatch.hotel.entities.Guest;
import com.cardlatch.hotel.entities.Room;
import com.cardlatch.hotel.entities.cust.GuestsInRoom;
import com.cardlatch.hotel.repos.GuestRepository;
import com.cardlatch.hotel.repos.RoomRepository;

@DataMongoTest
@RunWith(SpringRunner.class)
public class MongoDbRepositoryTest {
	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	RoomRepository roomRepo;

	@Autowired
	GuestRepository guestRepo;

	@Test
	public void testFindAvailableRooms() {
		roomRepo.deleteAll();

		Room availableRoom = new Room(1, 2);
		Room occupiedRoom = new Room(2, 3);

		mongoTemplate.save(availableRoom);
		mongoTemplate.save(occupiedRoom);

		List<Room> actual = roomRepo.findAvailableRooms(new int[] { 2 });

		assertEquals(1, actual.size());
		assertEquals(availableRoom, actual.get(0));

		actual = roomRepo.findAvailableRooms(new int[] { 1, 2 });
		assertEquals(0, actual.size());
	}

	@Test
	public void testFindRoomNumsWithCapacityGtThan() {
		roomRepo.deleteAll();

		mongoTemplate.save(new Room(1, 2));
		mongoTemplate.save(new Room(2, 3));
		mongoTemplate.save(new Room(3, 5));

		assertArrayEquals(new int[] { 1, 2, 3 }, roomRepo.findRoomNumsWithCapacityGtThan(0));
	}

	@Test
	public void testFindOccupiedRoomNumbers() {
		guestRepo.deleteAll();

		int[] occupiedRoomNums = guestRepo.findOccupiedRoomNumbers();
		assertArrayEquals(new int[] {}, occupiedRoomNums);

		mongoTemplate.save(new Guest("Guest1-1", 1));
		mongoTemplate.save(new Guest("Guest1-2", 1));
		mongoTemplate.save(new Guest("Guest2", 2));
		mongoTemplate.save(new Guest("Guest5", 5));

		occupiedRoomNums = guestRepo.findOccupiedRoomNumbers();
		Arrays.sort(occupiedRoomNums);

		assertArrayEquals(new int[] { 1, 2, 5 }, occupiedRoomNums);
	}

	@Test
	@Ignore("Command failed with error 15952 (Location15952): 'unknown group operator '$count'' on server localhost:63344")
	public void testFindPairOccupiedRoomNumbers() {
		guestRepo.deleteAll();

		int[] pairOccupiedRoomNums = guestRepo.findPairOccupiedRoomNumbers();
		assertArrayEquals(new int[] {}, pairOccupiedRoomNums);

		mongoTemplate.save(new Guest("Guest1-1", 1));
		mongoTemplate.save(new Guest("Guest1-2", 1));
		mongoTemplate.save(new Guest("Guest2", 2));
		mongoTemplate.save(new Guest("Guest6-1", 6));
		mongoTemplate.save(new Guest("Guest6-2", 6));
		mongoTemplate.save(new Guest("Guest6-3", 6));
		mongoTemplate.save(new Guest("Guest10-1", 10));
		mongoTemplate.save(new Guest("Guest10-2", 10));

		pairOccupiedRoomNums = guestRepo.findPairOccupiedRoomNumbers();
		Arrays.sort(pairOccupiedRoomNums);

		assertArrayEquals(new int[] { 1, 10 }, pairOccupiedRoomNums);
	}

	@Test
	public void testFindGuestsByRoomNums() {
		guestRepo.deleteAll();

		mongoTemplate.save(new Guest("Guest1-1", 1));
		mongoTemplate.save(new Guest("Guest1-2", 1));
		mongoTemplate.save(new Guest("Guest1-3", 1));

		GuestsInRoom expected = new GuestsInRoom(new String[] { "Guest1-1", "Guest1-2", "Guest1-3" }, 1);

		GuestsInRoom[] groupedGuestsActual = guestRepo.findGuestsByRoomNums(new int[] { 1 });

		assertEquals(1, groupedGuestsActual.length);
		assertArrayEquals(expected.getNames(), groupedGuestsActual[0].getNames());
	}
}
