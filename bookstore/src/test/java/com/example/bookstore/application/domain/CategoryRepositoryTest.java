package com.example.bookstore.application.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Should create and save Category")
    void testCreateCategory() {
        Category category = new Category();
        category.setName("Programming");

        Category saved = categoryRepository.save(category);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Programming");
    }

    @Test
    @DisplayName("Should find Category by name")
    void testSearchCategory() {
        Category category = new Category();
        category.setName("Fiction");
        categoryRepository.save(category);

        Category found = categoryRepository.findByName("Fiction");

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Fiction");
    }

    @Test
    @DisplayName("Should delete Category")
    void testDeleteCategory() {
        Category category = new Category();
        category.setName("History");
        Category saved = categoryRepository.save(category);

        categoryRepository.delete(saved);

        Category deleted = categoryRepository.findByName("History");
        assertThat(deleted).isNull(); // since findByName returns null if not found
    }
}