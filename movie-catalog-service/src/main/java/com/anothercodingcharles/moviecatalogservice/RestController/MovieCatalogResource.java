package com.anothercodingcharles.moviecatalogservice.RestController;

import com.anothercodingcharles.moviecatalogservice.Model.CatalogItem;
import com.anothercodingcharles.moviecatalogservice.Model.Movie;
import com.anothercodingcharles.moviecatalogservice.Model.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {


    private WebClient.Builder webClientBuilder;
    private  RestTemplate restTemplate;

    public MovieCatalogResource(RestTemplate restTemplate,WebClient.Builder webClientBuilder) {
        this.restTemplate = restTemplate;
        this.webClientBuilder = webClientBuilder;
    }

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId")  String userId){

        UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);

        //get all related movies id
       return  userRating.getRatings().stream().map(rating -> {
                Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
                /*
                Movie movie = webClientBuilder.build()
                        .get()
                        .uri("http://localhost:8082/movies/"+rating.getMovieId())
                        .retrieve()
                        .bodyToMono(Movie.class)
                        .block();
                        */
                return new CatalogItem(movie.getName(), "Action",rating.getRating());

        }).collect(Collectors.toList());

    }
}
