package com.paulopasinato.netfluxproject.repository;

import com.paulopasinato.netfluxproject.domain.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {
}
