package ru.clevertec.checkrunnerservlets.repository.impl;

import ru.clevertec.checkrunnerservlets.model.Product;
import ru.clevertec.checkrunnerservlets.repository.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ProductImpl implements Repository<Product> {

    private static final String PRODUCT_DB_NAME = "products";
    private static final String SELECT_ALL
            = "SELECT product_id, product_name, product_price, product_discount FROM " + PRODUCT_DB_NAME + " ORDER BY first_name, last_name";
    private static final String SELECT_ONE
            = "SELECT product_id, product_name, product_price, product_discount FROM " + PRODUCT_DB_NAME + " WHERE product_id=?";
    private static final String INSERT
            = "INSERT INTO " + PRODUCT_DB_NAME + " (product_name, product_price, product_discount) VALUES (?, ?, ?)";
    private static final String UPDATE
            = "UPDATE " + PRODUCT_DB_NAME + " SET product_name=?, product_price=?, product_discount=? WHERE product_id=?";
    private static final String DELETE
            = "DELETE FROM " + PRODUCT_DB_NAME + " WHERE product_id=?";
    private final DataSource dataSource;

    public ProductImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() {
        List<Product> productList = new CopyOnWriteArrayList<>();
        return null;
    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public Product update(Product entity) {
        return null;
    }

    @Override
    public Product insert(Product product) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT)) {
            long productId = -1L;
            pst.setString(1, product.getName());
            pst.setString(2, String.valueOf(product.getPrice()));
            pst.setString(3, String.valueOf(product.getDiscount()));
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                productId = gk.getLong("product_id");
            }
            gk.close();
            return product;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product delete(int id, Product entity) {
        return null;
    }
}
