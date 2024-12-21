package com.barmof.reactive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactiveApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(ReactiveApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ReactiveApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Flux<String> names = Flux.just("Luis", "Felipe", "", "Barrera", "Mora")
				.doOnNext(name -> {
					if (name.isEmpty()) {
						throw new RuntimeException("Name can't be empty");
					}
					System.out.println(name);
				});
		names.subscribe(e -> log.info(e),
				error -> log.error(error.getMessage()));
	}
}
