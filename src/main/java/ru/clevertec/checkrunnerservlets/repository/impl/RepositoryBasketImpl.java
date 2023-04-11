package ru.clevertec.checkrunnerservlets.repository.impl;

import lombok.AllArgsConstructor;
import ru.clevertec.checkrunnerservlets.model.Basket;
import ru.clevertec.checkrunnerservlets.repository.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
public class RepositoryBasketImpl implements Repository<Basket, Long> {

    private final Connection connection;

    @Override
    public List<Basket> findAll() {
        return null;
    }

    @Override
    public Basket find(Long aLong) {
        return null;
    }

    @Override
    public boolean update(Long aLong, Basket basket) {
        return false;
    }

    @Override
    public boolean insert(Basket basket) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement((SQLBasket.INSERT.QUERY))) {
            statement.setString(1, basket.getNameProduct());
            statement.setDouble(2, basket.getPriceProduct());
            statement.setBoolean(3, basket.getDiscountProduct());
            statement.setDouble(4, basket.getQuantityProduct());
            statement.setDouble(5, basket.getTotalPrice());
            statement.setDouble(6, basket.getDiscountCardPercent());
            statement.setDouble(7, basket.getTotalDiscount());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean delete(Long aLong) {
        return false;
    }

    /**
     SQL queries for products table in to basket.
     */
    enum SQLBasket {
        INSERT("INSERT INTO basket (id,name_product, price_product, discount_product,quantity_product,total_price,discount_card_percent,total_discount) VALUES (DEFAULT,(?), (?), (?),(?), (?), (?), (?)) RETURNING id"),
        GET("SELECT id_product, name_product, price_product,discount_product  FROM products WHERE id_product = (?)"),
        DELETE("DELETE FROM products WHERE id_product = (?) RETURNING id_product"),
        UPDATE("UPDATE products SET name_product = (?),price_product= (?), discount_product= (?) WHERE id_product= (?) RETURNING id_product"),
        GET_ALL("SELECT * FROM products");

        final String QUERY;

        SQLBasket(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
