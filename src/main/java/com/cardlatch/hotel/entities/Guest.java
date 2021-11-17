package com.cardlatch.hotel.entities;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class Guest {
	@Id
	private String _id;
	private String name;
	private int room;

	public Guest() {
	}

	public Guest(String name, int room) {
		this.name = name;
		this.room = room;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRoom() {
		return this.room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	@Override
	public String toString() {
		StringBuilder guest = new StringBuilder("Guest [_id=");

		guest.append(this._id);
		guest.append(", name=");
		guest.append(this.name);
		guest.append(", room=");
		guest.append(this.room);

		return guest.toString();
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof Guest))
			return false;
		Guest guest = (Guest) o;
		return Objects.equals(this._id, guest._id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this._id);
	}
}
