package com.cardlatch.hotel;

import com.cardlatch.hotel.entities.Room;
import com.cardlatch.hotel.entities.cust.GuestsInRoom;
import com.cardlatch.hotel.repos.GuestRepository;
import com.cardlatch.hotel.repos.RoomRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HotelController {
	private final RoomRepository roomRepo;
	private final GuestRepository guestRepo;

	HotelController(RoomRepository roomRepo, GuestRepository guestRepo) {
		this.roomRepo = roomRepo;
		this.guestRepo = guestRepo;
	}

	@GetMapping("/rooms/available")
	public List<Room> rooms() {
		int[] occupiedRoomNumbers = guestRepo.findOccupiedRoomNumbers();
		return roomRepo.findAvailableRooms(occupiedRoomNumbers);
	}

	@GetMapping("/rooms/couples")
	public List<Room> couples() {
		int[] pairOccupiedRoomNumbers = guestRepo.findPairOccupiedRoomNumbers();
		return roomRepo.findOccupiedRooms(pairOccupiedRoomNumbers);
	}
	
	@GetMapping("/guests/families")
	public GuestsInRoom[] families() {
		int[] roomNums = roomRepo.findRoomNumsWithCapacityGtThan(2);
		return guestRepo.findGuestsByRoomNums(roomNums);
	}
}
