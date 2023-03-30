package com.microservices.ratingservice.services;

import com.microservices.ratingservice.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating createRating(Rating rating);

    Rating getRatingByRatingId(String ratingId);

    List<Rating> getRatingsByUserId(String userId);

    List<Rating> getRatingsByHotelId(String hotelId);

    List<Rating> getAllRatings();

}
