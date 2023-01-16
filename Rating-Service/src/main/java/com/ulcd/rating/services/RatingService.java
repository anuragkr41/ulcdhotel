package com.ulcd.rating.services;

import java.util.List;

import com.ulcd.rating.entities.Rating;

public interface RatingService {

	// create Rating
	Rating create(Rating rating);

	// get all ratings
	List<Rating> getRatings();

	// get all by user id
	List<Rating> getRatingByUserId(String userID);

	// get all by hotel
	List<Rating> getRatingByHotelId(String userID);

}
