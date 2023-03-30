package com.microservices.ratingservice.services.impl;

import com.microservices.ratingservice.entities.Rating;
import com.microservices.ratingservice.exceptions.ResourceNotFoundException;
import com.microservices.ratingservice.repositories.RatingRepository;
import com.microservices.ratingservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating getRatingByRatingId(String ratingId) {
        return ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating not found!"));
    }

    @Override
    public List<Rating> getRatingsByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingsByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }
}
