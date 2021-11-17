package com.cardlatch.hotel.entities;

import java.util.Objects;

import org.springframework.data.annotation.Id;

public class Room {
	@Id
	private String _id;
	private int number;
	private int capacity;

	public Room() {
	}

	public Room(int number, int capacity) {
		this.number = number;
		this.capacity = capacity;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		StringBuilder room = new StringBuilder("Room [_id=");

		room.append(this._id);
		room.append(", number=");
		room.append(this.number);
		room.append(", capacity=");
		room.append(this.capacity);

		return room.toString();
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof Room))
			return false;
		Room room = (Room) o;
		return Objects.equals(this._id, room._id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this._id);
	}
}
