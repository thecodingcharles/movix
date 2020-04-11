package com.anothercodingcharles.movieratingservice.RestController;

import com.anothercodingcharles.movieratingservice.Model.Rating;
import com.anothercodingcharles.movieratingservice.Model.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable ("movieId") String movieId){
        return new Rating(movieId,5);
    }

    @GetMapping("users/{userId}")
    public UserRating getUserRatings(@PathVariable ("userId") String userId){
        List<Rating> ratings =  Arrays.asList(
                new Rating("888",3),
                new Rating("888",3));
        return new UserRating(ratings);
    }
}
