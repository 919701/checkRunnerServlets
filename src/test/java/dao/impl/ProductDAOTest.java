package dao.impl;

import dao.DAO;
import lombok.NonNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.checkrunnerservlets.model.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductDAOTest {

    @NonNull
    private DAO<Product, Long> dao;

    @NonNull
    private Connection connection;


    @BeforeEach
    void setUp() {
        try {
            String user = "postgres";
            String password = "12345678";
            String url = "jdbc:postgresql://localhost:5432/";
            connection = DriverManager.getConnection(url, user, password);
            dao = new ProductDAO(connection);
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
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
    void create() {
        Product product = new Product("123", 123d, false);

        assertTrue(dao.create(product));
    }

    @Test
    void read() {
        final Product product = dao.read(1L);

        assertEquals("Potato", product.getName());
        dao.delete(product.getId());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
        assertTrue(dao.delete(13L));
    }
}