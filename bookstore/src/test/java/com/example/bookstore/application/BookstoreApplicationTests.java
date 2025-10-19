package com.example.bookstore.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.bookstore.application.web.BookController;
import com.example.bookstore.application.web.BookRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookstoreApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private BookController bookController;

	@Autowired
	private BookRestController bookRestController;

	@Test
	void contextLoads() {
		assertThat(bookController).isNotNull();
		assertThat(bookRestController).isNotNull();
	}

	@Test
	void smokeTestBookControllerEndpoint() {
		String response = this.restTemplate.getForObject("http://localhost:" + port + "/books", String.class);
		assertThat(response).isNotNull();
	}

	@Test
	void smokeTestBookRestControllerEndpoint() {
		String response = this.restTemplate.getForObject("http://localhost:" + port + "/api/books", String.class);
		assertThat(response).isNotNull();
	}
}