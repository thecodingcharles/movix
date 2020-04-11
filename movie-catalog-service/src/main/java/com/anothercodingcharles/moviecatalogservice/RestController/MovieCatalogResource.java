package com.anothercodingcharles.moviecatalogservice.RestController;

import com.anothercodingcharles.moviecatalogservice.Model.CatalogItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId")  String userId){

        return Collections.singletonList(new CatalogItem("Transformers", "Action",7));
    }
}
