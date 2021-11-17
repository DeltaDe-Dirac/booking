package com.cardlatch.hotel.repos;

import com.cardlatch.hotel.entities.Room;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RoomRepository extends MongoRepository<Room, String> {
	@Query("{ number : { $nin: ?0 } }")
	List<Room> findAvailableRooms(int[] occupiedNumbers);

	@Query("{ number : { $in: ?0 } }")
	List<Room> findOccupiedRooms(int[] occupiedNumbers);

	@Aggregation(pipeline = { "{ $match: { capacity: { $gt: ?0 } } }", "{ $project: { number: 1 } }" })
	int[] findRoomNumsWithCapacityGtThan(int capacity);
}