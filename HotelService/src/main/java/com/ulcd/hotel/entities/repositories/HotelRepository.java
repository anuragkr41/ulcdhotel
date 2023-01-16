package com.ulcd.hotel.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ulcd.hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {
	
	

}
