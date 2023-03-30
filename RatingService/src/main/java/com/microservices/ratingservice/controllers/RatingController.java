package com.microservices.ratingservice.controllers;

import com.microservices.ratingservice.entities.Rating;
import com.microservices.ratingservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @PostMapping
    private ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        return new ResponseEntity<>(ratingService.createRating(rating), HttpStatus.CREATED);
    }

    @GetMapping()
    private ResponseEntity<List<Rating>> getAllRatings(){
        return new ResponseEntity<>(ratingService.getAllRatings(), HttpStatus.OK);
    }

    @GetMapping("/{ratingId}")
    private ResponseEntity<Rating> getRatingById(@PathVariable String ratingId){
        return new ResponseEntity<>(ratingService.getRatingByRatingId(ratingId), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    private ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
        return new ResponseEntity<>(ratingService.getRatingsByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/hotels/{hotelId}")
    private ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
        return new ResponseEntity<>(ratingService.getRatingsByHotelId(hotelId), HttpStatus.OK);
    }
}
