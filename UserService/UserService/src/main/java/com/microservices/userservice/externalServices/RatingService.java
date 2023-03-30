package com.microservices.userservice.externalServices;

import com.microservices.userservice.entities.Hotel;
import com.microservices.userservice.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;


//name is either IP:PORT or Service name is registered in Eureka
@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    @GetMapping("/ratings/users/{userId}")
    ArrayList<Rating> getRatingByUserId(@PathVariable String userId);
}
