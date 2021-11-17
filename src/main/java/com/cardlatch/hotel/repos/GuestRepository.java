package com.cardlatch.hotel.repos;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.cardlatch.hotel.entities.Guest;
import com.cardlatch.hotel.entities.cust.GuestsInRoom;

public interface GuestRepository extends MongoRepository<Guest, String> {
	@Aggregation(pipeline = { "{ $group: { _id : $room } }" })
	int[] findOccupiedRoomNumbers();

	@Aggregation(pipeline = { "{ $group: { _id : $room, guestsInRoom: { $count: {} } } }",
			"{ $match: { guestsInRoom: { $eq: 2 } } }", "{ $project: { room: 1 } }" })
	int[] findPairOccupiedRoomNumbers();

	@Aggregation(pipeline = { "{ $match: { room: { $in: ?0 } } }",
			"{ $group: { _id : $room, names: { $push: $name } } }", "{ $project: { _id:0, room: $_id, names: 1 } }" })
	GuestsInRoom[] findGuestsByRoomNums(int[] roomNums);
}