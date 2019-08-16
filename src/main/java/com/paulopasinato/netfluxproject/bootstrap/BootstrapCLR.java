package com.paulopasinato.netfluxproject.bootstrap;

import com.paulopasinato.netfluxproject.domain.Movie;
import com.paulopasinato.netfluxproject.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import reactor.core.publisher.Flux;

import java.util.UUID;

public class BootstrapCLR implements CommandLineRunner {

    private final MovieRepository movieRepository;

    public BootstrapCLR(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //clear old data
        movieRepository.deleteAll()
                .thenMany(
                        Flux.just("Silence of the Lambdas", "AEon Flux", "Enter the Mono<Void>", "The Fluxxinator",
                                "Back to the Future", "Meet the Fluxes", "Lord of the Fluxes")
                                .map(title -> new Movie(UUID.randomUUID().toString(), title))
                                .flatMap(movieRepository::save))
                                .subscribe(null, null, () -> {
                                    movieRepository.findAll().subscribe(System.out::println);
                                });
    }
}
