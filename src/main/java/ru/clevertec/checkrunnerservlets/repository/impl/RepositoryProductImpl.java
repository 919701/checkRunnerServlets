package ru.clevertec.checkrunnerservlets.repository.impl;

import lombok.NonNull;
import ru.clevertec.checkrunnerservlets.model.Product;
import ru.clevertec.checkrunnerservlets.repository.Repository;

import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RepositoryProductImpl implements Repository<Product, Long> {

    /**
     Connection of database.
     */
    @NonNull
    private final Connection connection;

    /**
     Init database connection.

     @param connection of database.
     */
    public RepositoryProductImpl(@NonNull Connection connection) {
        this.connection = connection;
    }

    /**
     Create Product in database.

     @param product for create.
     @return false if User already exist. If creating success true.
     */
    @Override
    public boolean insert(@NonNull final Product product) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement((SQLProduct.INSERT.QUERY))) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setBoolean(3, product.getDiscount());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new CopyOnWriteArrayList<>();

        try (Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(SQLProduct.GET_ALL.QUERY);
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getLong("id_product"),
                        resultSet.getString("name_product"),
                        resultSet.getDouble("price_product"),
                        resultSet.getBoolean("discount_product")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return products;
    }

    /**
     Select product by ID

     @param id for select
     @return valid entity if she exists. If entity does not exist return empty User with id = -1.
     */
    @Override
    public Product find(@NonNull final Long id) {
        final Product product = new Product();

        try (PreparedStatement statement = connection.prepareStatement((SQLProduct.GET.QUERY))) {
            statement.setLong(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product.setId(resultSet.getLong("id_product"));
                product.setName(resultSet.getString("name_product"));
                product.setPrice(resultSet.getDouble("price_product"));
                product.setDiscount(resultSet.getBoolean("discount_product"));
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return product;
    }

    /**
     Update products by id

     @param id         product upgrade
     @param newProduct updated product
     @return status update
     */
    @Override
    public boolean update(Long id, Product newProduct) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLProduct.UPDATE.QUERY)) {
            statement.setString(1, newProduct.getName());
            statement.setDouble(2, newProduct.getPrice());
            statement.setBoolean(3, newProduct.getDiscount());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     Delete product by ID.

     @param id for delete.
     @return true if Product was deleted. False if Product not exist.
     */
    @Override
    public boolean delete(@NonNull Long id) {

        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(SQLProduct.DELETE.QUERY)) {
            statement.setLong(1, id);
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    enum SQLProduct {
        INSERT("INSERT INTO products (id_product,name_product, price_product, discount_product) VALUES (DEFAULT,(?), (?), (?)) RETURNING id_product"),
        GET("SELECT id_product, name_product, price_product,discount_product  FROM products WHERE id_product = (?)"),
        DELETE("DELETE FROM products WHERE id_product = (?)  RETURNING id_product"),
        UPDATE("UPDATE products SET name_product = (?),price_product= (?), discount_product= (?) WHERE id_product= (?) RETURNING id_product"),
        GET_ALL("SELECT * FROM products");

        String QUERY;

        SQLProduct(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}