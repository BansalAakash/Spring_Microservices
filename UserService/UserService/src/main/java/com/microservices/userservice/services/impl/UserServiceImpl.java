package com.microservices.userservice.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.microservices.userservice.entities.Hotel;
import com.microservices.userservice.entities.Rating;
import com.microservices.userservice.entities.User;
import com.microservices.userservice.exceptions.ResourceNotFoundException;
import com.microservices.userservice.externalServices.HotelService;
import com.microservices.userservice.externalServices.RatingService;
import com.microservices.userservice.repositories.UserRepository;
import com.microservices.userservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static String RATING_API_URL = "http://RATINGSERVICE/ratings/users/";
    private static String HOTEL_API_URL = "http://HOTELSERVICE/hotels/";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        //generate uinque userId
        String randomId = UUID.randomUUID().toString();
        user.setUserId(randomId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            Rating[] ratings = restTemplate.getForObject(RATING_API_URL + user.getUserId(), Rating[].class);
            if (ratings != null) {
                List<Rating> userRatings = Arrays.stream(ratings).toList();
                user.setRating(userRatings);
            }
            for (Rating rating : user.getRating()) {
                rating.setHotel(restTemplate.getForObject(HOTEL_API_URL + rating.getHotelId(), Hotel.class));
            }
        }
        return users;
    }

    //Using Feign
    //To enable Feign, add the dependency, then do @EnableFeignClients in the main class
    //and then create an interface like externalServices.HotelService and externalServices.RatingService
    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(String.format("User with ID: %s is not present on server!", userId)));

        ArrayList<Rating> ratings = ratingService.getRatingByUserId(userId);
            for (Rating rating : ratings)
                rating.setHotel(hotelService.getHotel(rating.getHotelId()));
            user.setRating(ratings);

        return user;
    }

//    Using RestTemplate
//    To enable RestTemplate, we need to autowire it. To autowire it, we need to add a Bean for RestTemplate.
//    We have added the resttemplate bean in config.MyConfig Configuration class.
//    @Override
//    public User getUser(String userId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(String.format("User with ID: %s is not present on server!", userId)));
//
//        Rating[] ratings = restTemplate.getForObject(RATING_API_URL + userId, Rating[].class);
//        if(ratings != null) {
//            for (Rating rating : ratings)
//                rating.setHotel(restTemplate.getForObject(HOTEL_API_URL + rating.getHotelId(), Hotel.class));
//            user.setRating(Arrays.stream(ratings).toList());
//        }
//
//        return user;
//    }
}
