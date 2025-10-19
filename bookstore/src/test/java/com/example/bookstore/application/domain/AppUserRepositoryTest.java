package com.example.bookstore.application.domain;

import static org.assertj.core.api.Assertions.assertThat;

//import com.example.bookstore.model.AppUser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    @DisplayName("Should create and save an AppUser")
    void testCreateUser() {
        AppUser user = new AppUser();
        user.setUsername("john_doe");
        user.setPasswordHash("password123");
        user.setEmail("john@example.com"); // assuming your entity has an email field
        user.setRole("USER");
        AppUser saved = appUserRepository.save(user);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getUsername()).isEqualTo("john_doe");
    }

    @Test
    @DisplayName("Should find AppUser by username")
    void testFindByUsername() {
        AppUser user = new AppUser();
        user.setUsername("jane_doe");
        user.setPasswordHash("secret");
        user.setEmail("jane@example.com");
        user.setRole("ADMIN");
        appUserRepository.save(user);

        AppUser found = appUserRepository.findByUsername("jane_doe");

        assertThat(found).isNotNull();
        assertThat(found.getUsername()).isEqualTo("jane_doe");
    }

    @Test
    @DisplayName("Should delete AppUser")
    void testDeleteUser() {
        AppUser user = new AppUser();
        user.setUsername("mark_twain");
        user.setPasswordHash("pass");
        user.setEmail("mark@example.com");
        user.setRole("USER");
        AppUser saved = appUserRepository.save(user);

        appUserRepository.delete(saved);

        AppUser found = appUserRepository.findByUsername("mark_twain");
        assertThat(found).isNull();
    }
}