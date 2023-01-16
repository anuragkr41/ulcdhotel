package com.ulcd.hotel.services;

import java.util.List;

import com.ulcd.hotel.entities.Hotel;

public interface HotelService {

	//create
	Hotel create(Hotel hotel);
	
	//getAll
	List<Hotel> getAllHotels();
	
	//get single hotel
	Hotel getHotel(String id);
	
}
