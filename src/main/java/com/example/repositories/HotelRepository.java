package com.example.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.model.Hotel;
import com.example.model.Room;

public class HotelRepository {
	
	public List<Hotel> GetAllHotels(){
		ArrayList<Hotel> hotels = new ArrayList<Hotel>();
		for (int i = 0; i < 10; i++) {
			Hotel h = new Hotel();
			h.id = i;
			h.name = "Hotel " + i;
			h.city = "City " + i;
			h.Rating = i;
			ArrayList<Room> rooms = new ArrayList<Room>();
			for(int j = 0; j<5; j++) {
				Room r = new Room();
				r.id = j;
				r.name = "Room "+j;
				r.description =" Voici la Room "+ j;
				rooms.add(r);
			}
			h.Room = rooms;
			hotels.add(h);
		}
		return hotels;
	}

	
	public Optional<Hotel> GetHotel(int id){
		List<Hotel> hotels = GetAllHotels();
		Optional<Hotel> hotel = hotels.stream().filter(h -> h.id == id).findFirst();
		return hotel;
	}
	
	public Optional<Room> GetRoomForHotel(int hotelid, int roomid){
		Optional<Hotel> hotel = GetHotel(hotelid);
		if(hotel.isPresent()) {
			List<Room>rooms = hotel.get().Room;
			Optional<Room>room = rooms.stream().filter(r -> r.id == roomid).findFirst();
			return room;
		}
		return Optional.empty();
	}
}
