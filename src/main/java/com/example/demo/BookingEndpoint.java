package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.contrat.BookingContract;
import com.example.demo.service.BookingService;
import com.example.model.Booking;


@RestController
public class BookingEndpoint {

	
	@PostMapping("/book/hotel/{hotelId}/rooms/{roomId}")
	public double bookHotel(@PathVariable(value="hotelId") int hotelId,
			@PathVariable(value="roomId")int roomId,
			@RequestBody BookingContract booking) {
				Booking book = new Booking();
				book.clientId = booking.clientId;
				book.hotelId = hotelId;
				book.roomId = roomId;
				try {
					book.arrival = new SimpleDateFormat("dd/MM/yyyy").parse(booking.arrival);
					book.departure = new SimpleDateFormat("dd/MM/yyyy").parse(booking.departure);
				} catch (Exception e) {
					// TODO: handle exception
					throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Invalid date format");
				}
				
				return new BookingService().bookHotel(hotelId, roomId, book);
				
	}
	
	@GetMapping(value="/book/{userId}")
	public List<Booking> getMethodName(@PathVariable(value="userId")int userId){
		List<Booking> booking = new ArrayList<Booking>();
		booking = new BookingService().GetBookingForUser(userId);
		return booking;
	}
	
	@DeleteMapping(value="/book/delete/{bookingId}")
	public void DeleteBooking(@PathVariable(value = "bookingId") int bookingId) {
		new BookingService().DeleteBooking(bookingId);
	}
	
//	@GetMapping(value="/api/hotels/{id}/rooms/{id}/availabilities")

}
