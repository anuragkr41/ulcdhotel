package com.ulcd.hotel.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ulcd.hotel.entities.Hotel;
import com.ulcd.hotel.entities.repositories.HotelRepository;
import com.ulcd.hotel.exceptions.ResourceNotFoundException;
import com.ulcd.hotel.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	HotelRepository hotelRepository;

	@Override
	public Hotel create(Hotel hotel) {
		// TODO Auto-generated method stub
		
		hotel.setId(UUID.randomUUID().toString());
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotels() {
		// TODO Auto-generated method stub
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotel(String id) {
		// TODO Auto-generated method stub
		return hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with given id not found"));
	}

}
