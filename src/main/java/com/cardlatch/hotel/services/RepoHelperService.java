package com.cardlatch.hotel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cardlatch.hotel.repos.GuestRepository;
import com.cardlatch.hotel.repos.RoomRepository;

@Component
public class RepoHelperService {
	@Autowired
	private final RoomRepository roomRepo;
	@Autowired
	private final GuestRepository guestRepo;

	RepoHelperService(RoomRepository roomRepo, GuestRepository guestRepo) {
		this.roomRepo = roomRepo;
		this.guestRepo = guestRepo;
	}

	public String getHotelOccupancy() {
		double occupiedRooms = guestRepo.findOccupiedRoomNumbers().length;
		double allRooms = roomRepo.findAll().size();

		if (allRooms == 0) {
			return "Hotel occupancy is: 0%";
		}

		return String.format("Hotel occupancy is: %s%%", Math.round(occupiedRooms / allRooms * 100.0));
	}
}
