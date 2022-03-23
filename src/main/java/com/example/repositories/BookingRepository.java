package com.example.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.model.Booking;
import com.example.repositories.DTO.BookingDTO;

public class BookingRepository {


	public static HashMap<Integer, BookingDTO> bookingRepository;
	public static int current_index = 0;

	public BookingRepository() {
		// TODO Auto-generated constructor stub

		if(bookingRepository == null) {
			bookingRepository = new HashMap<Integer,BookingDTO>();

		}
	}

	public void InsertBooking(Booking book) {
		BookingDTO booking = new BookingDTO();
		booking.hotelId = book.hotelId;
		booking.roomId = book.roomId;
		booking.clientId = book.clientId;
		booking.arrival = book.arrival;
		booking.departure = book.departure;
		bookingRepository.put(current_index, booking);
		current_index++;
	}

	public List<Booking> GetBookingForUser(int userId){
		List<Booking> bookings = new ArrayList<Booking>();
		for(BookingDTO booking : bookingRepository.values()) {
			if(booking.clientId == userId) {
				Booking book = new Booking();
				book.hotelId = booking.hotelId;
				book.roomId = booking.roomId;
				book.clientId= booking.clientId;
				book.arrival = booking.arrival;
				book.departure= booking.departure;
				bookings.add(book);
			}
		}
		return bookings;
	}

	public void DeleteBooking(int bookingId) {
		// TODO Auto-generated method stub
		 bookingRepository.remove(bookingId);
	}
}
