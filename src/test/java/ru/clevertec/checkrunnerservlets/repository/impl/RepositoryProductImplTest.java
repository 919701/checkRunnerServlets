package ru.clevertec.checkrunnerservlets.repository.impl;

import lombok.NonNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.checkrunnerservlets.model.Product;
import ru.clevertec.checkrunnerservlets.repository.Repository;
import ru.clevertec.checkrunnerservlets.util.connection.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryProductImplTest {

    @NonNull
    private Connection connection;

    @NonNull
    private Repository<Product, Long> repository;


    @BeforeEach
    void setUp() {
        connection = ConnectionManager.open();
        repository = new RepositoryProductImpl(connection);
    }

    @AfterEach
    void tearDown() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void insert() {
        Product product = new Product("123", 123d, false);

        assertTrue(repository.insert(product));
    }

    @Test
    void findAll() {
        List<Product> productList = repository.findAll();
        System.out.println(productList);
    }

    @Test
    void find() {
    }

    @Test
    void update() {
        Product product = new Product("updateNewProduct25", 15d, true);
        assertTrue(repository.update(15L, product));
    }

    @Test
    void delete() {
    }
}