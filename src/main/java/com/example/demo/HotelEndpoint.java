package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.contrat.GetHotelContract;
import com.example.contrat.GetHotelsContract;
import com.example.contrat.GetRoomContract;
import com.example.contrat.GetRoomsContract;
import com.example.contrat.HotelContract;
import com.example.contrat.RoomContract;
import com.example.demo.service.HotelService;
import com.example.model.Hotel;
import com.example.model.Room;

@RestController
public class HotelEndpoint {

	@GetMapping("/hotel")
		public GetHotelsContract getHotels() {
			List<Hotel> hotels = new HotelService().GetAllHotels();
			GetHotelsContract response = new GetHotelsContract();
			response.hotels = new ArrayList<HotelContract>();
			for(Hotel h : hotels) {
				HotelContract hotel = new HotelContract();
				hotel.id = h.id;
				hotel.name = h.name;
				hotel.city = h.city;
				response.hotels.add(hotel);
			}
			return response;
		}
	
	@GetMapping("/hotel/{id}")
	public GetHotelContract getHotels(@PathVariable(value="id")int id) {
		Hotel hotel;
		try {
			hotel = new HotelService().GetAllHotels().stream().filter(h -> h.id == id).findFirst().get();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Hotel not found ! ");
		}
		
		GetHotelContract response = new GetHotelContract();
		
		response.id = hotel.id;
		response.name = hotel.name;
		response.city = hotel.city;
		response.rooms = new ArrayList<RoomContract>();
		for(Room r : hotel.Room) {
			RoomContract room = new RoomContract();
			room.id = r.id;
			room.name = r.name;
			room.description = r.description;
			response.rooms.add(room);
		}
		
		return response;
	}
	
	@GetMapping("/hotel/{id}/rooms")
	public GetRoomsContract getRooms(@PathVariable(value="id")int id) {
		Hotel hotel;
		try {
			hotel = new HotelService().GetAllHotels().stream().filter(h -> h.id == id).findFirst().get();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Hotel not found ! ");
		}
		
		GetRoomsContract response = new GetRoomsContract();
		response.rooms = new ArrayList<RoomContract>();
		for(Room r : hotel.Room) {
			RoomContract room = new RoomContract();
			room.id = r.id;
			room.name = r.name;
			room.description = r.description;
			response.rooms.add(room);
		}
		
		return response;
		
	}
	
	@GetMapping("/hotel/{id}/rooms/{roomid}")
	public GetRoomContract getRoom(@PathVariable(value="id")int id, @PathVariable(value="roomid")int roomid) {
		Optional<Room> room;
		try {
			room = new HotelService().GetRoomForHotel(id, roomid);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Hotel or Room not found ! ");
		}
		GetRoomContract response =  new GetRoomContract();
		response.id = room.get().id;
		response.name = room.get().name;
		response.description = room.get().description;
		response.price = room.get().price;
		return response;
	}
}


