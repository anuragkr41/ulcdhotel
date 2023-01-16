package com.ulcd.rating.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ulcd.rating.entities.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {
	
	//Cutom find methods
	
	List<Rating>findByUserId(String userId);
	List<Rating>findByHotelId(String hotelId);

}
