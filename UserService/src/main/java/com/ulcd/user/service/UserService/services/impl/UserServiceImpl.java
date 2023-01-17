package com.ulcd.user.service.UserService.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ulcd.user.service.UserService.entities.Hotel;
import com.ulcd.user.service.UserService.entities.Rating;
import com.ulcd.user.service.UserService.entities.User;
import com.ulcd.user.service.UserService.exceptions.ResourceNotFoundException;
import com.ulcd.user.service.UserService.repositories.UserRepository;
import com.ulcd.user.service.UserService.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;
//	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		// generate unique user Id

		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {

		List<User> allUsers = userRepository.findAll();

		return allUsers;
	}

	// This is for finding a single user
	@Override
	public User getUser(String userId) {
		// get user from database with the help of user repository.
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User with the given id is not found on server !! " + userId));

		// fetch the ratings of the above user from Rating service
		// http://localhost:8083/ratings/users/09d4726f-c6d1-4749-ba62-50f8bca31361

		Rating[] ratingsOfUSer = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(),
				Rating[].class);

		List<Rating> ratings = Arrays.stream(ratingsOfUSer).toList();

		//

		List<Rating> ratingList = ratings.stream().map(rating -> {
			System.out.println(rating.getHotelId());
			ResponseEntity<Hotel> forEntity = restTemplate
					.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
			Hotel hotel = forEntity.getBody();
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());

		user.setRatings(ratingList);
		return user;
	}

}
