package com.anothercodingcharles.moviecatalogservice.RestController;

import com.anothercodingcharles.moviecatalogservice.Model.CatalogItem;
import com.anothercodingcharles.moviecatalogservice.Model.Movie;
import com.anothercodingcharles.moviecatalogservice.Model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId")  String userId){


        List<Rating> ratings =  Arrays.asList(new Rating("888",6),
                                 new Rating("888",10)
        );
        //get all related movies id
       return  ratings.stream().map(rating -> {
               Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
                return new CatalogItem(movie.getName(), "Action",rating.getRating());

        }).collect(Collectors.toList());

    }
}
