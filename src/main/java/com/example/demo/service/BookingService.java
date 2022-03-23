package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.model.Booking;
import com.example.repositories.BookingRepository;
import com.example.repositories.DTO.BookingDTO;

public class BookingService {
	
	BookingRepository Bkr = new BookingRepository();
	HashMap<Integer, BookingDTO> bookingRepository = Bkr.bookingRepository;
	
	public double bookHotel(int hotelId, int roomId, Booking book) {
		// TODO Auto-generated method stub
		BookingRepository b = new BookingRepository();
		b.InsertBooking(book);
		return 0;
	}
	
	
	public void InsertBooking(Booking book) {
		BookingRepository b = new BookingRepository();
		b.InsertBooking(book);
		
	}
	
	public List<Booking> GetBookingForUser(int userId){
		List<Booking> bookings = new ArrayList<Booking>();
		for(BookingDTO booking : bookingRepository.values()) {
			Booking book = new Booking();
			book.hotelId = booking.hotelId;
			book.roomId = booking.roomId;
			book.clientId= booking.clientId;
			book.arrival = booking.arrival;
			book.departure= booking.departure;
			bookings.add(book);
		}
		return bookings;
	}


	public void DeleteBooking(int bookingId) {
		// TODO Auto-generated method stub
		  new BookingRepository().DeleteBooking(bookingId);
	}



}
