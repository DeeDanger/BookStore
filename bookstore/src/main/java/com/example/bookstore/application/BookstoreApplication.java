package com.example.bookstore.application;

import com.example.bookstore.application.domain.AppUser;
import com.example.bookstore.application.domain.AppUserRepository;
import com.example.bookstore.application.domain.Book;
import com.example.bookstore.application.domain.BookRepository;
import com.example.bookstore.application.domain.Category;
import com.example.bookstore.application.domain.CategoryRepository;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository,
			AppUserRepository urepository) {
		return (args) -> {
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
					"user@user.com", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					"admin@admin.com", "ADMIN");

			urepository.save(user1);
			urepository.save(user2);

			Category classics = new Category("Classics");
			Category sciFi = new Category("Science Fiction");
			Category fantasy = new Category("Fantasy");
			Category mystery = new Category("Mystery");

			crepository.saveAll(List.of(classics, sciFi, fantasy, mystery));

			repository
					.save(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "9780743273565", 19.99, classics));
			repository.save(new Book("To Kill a Mockingbird", "Harper Lee", 1960, "9780061120084", 19.99, classics));
			repository.save(new Book("1984", "George Orwell", 1949, "9780451524935", 19.99, sciFi));
			repository.save(new Book("Brave New World", "Aldous Huxley", 1932, "9780060850524", 19.99, sciFi));
			repository
					.save(new Book("The Catcher in the Rye", "J.D. Salinger", 1951, "9780316769488", 19.99, classics));
			repository.save(new Book("The Hobbit", "J.R.R. Tolkien", 1937, "9780547928227", 19.99, fantasy));

		};
	}
}
