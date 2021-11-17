package com.cardlatch.hotel.entities.cust;

public class GuestsInRoom {
	private String[] names;
	private int room;

	public GuestsInRoom() {
	}

	public GuestsInRoom(String[] names, int room) {
		this.names = names;
		this.room = room;
	}

	public String[] getNames() {
		return names;
	}

	public void setNames(String[] names) {
		this.names = names;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}
}
