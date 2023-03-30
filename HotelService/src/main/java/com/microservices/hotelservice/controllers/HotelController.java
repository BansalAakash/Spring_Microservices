package com.microservices.hotelservice.controllers;


import com.microservices.hotelservice.entities.Hotel;
import com.microservices.hotelservice.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    private ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return new ResponseEntity<>(hotelService.create(hotel), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Hotel> getHotelById(@PathVariable String id) {
        return new ResponseEntity<>(hotelService.get(id), HttpStatus.OK);
    }

    @GetMapping()
    private ResponseEntity<List<Hotel>> getAllHotels() {
        return new ResponseEntity<>(hotelService.getAll(), HttpStatus.OK);
    }

}
