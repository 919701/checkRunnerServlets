package ru.clevertec.checkrunnerservlets.repository.impl;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import ru.clevertec.checkrunnerservlets.model.Basket;
import ru.clevertec.checkrunnerservlets.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@AllArgsConstructor
public class RepositoryBasketImpl implements Repository<Basket, Long> {

    private final Connection connection;

    @Override
    public List<Basket> findAll() {
        List<Basket> listBasket = new CopyOnWriteArrayList<>();

        try (Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(SQLBasket.GET_ALL.QUERY);
            while (resultSet.next()) {
                listBasket.add(new Basket(
                        resultSet.getLong("id"),
                        resultSet.getString("name_product"),
                        resultSet.getDouble("price_product"),
                        resultSet.getDouble("quantity_product"),
                        resultSet.getBoolean("discount_product"),
                        resultSet.getDouble("total_price"),
                        resultSet.getDouble("discount_card_percent"),
                        resultSet.getDouble("total_discount")
                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return listBasket;
    }

    @Override
    public Basket find(Long id) {
        final Basket product = new Basket();

        try (PreparedStatement statement = connection.prepareStatement((SQLBasket.GET.QUERY))) {
            statement.setLong(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product.setId(resultSet.getLong("id"));
                product.setNameProduct(resultSet.getString("name_product"));
                product.setPriceProduct(resultSet.getDouble("price_product"));
                product.setQuantityProduct(resultSet.getDouble("quantity_product"));
                product.setDiscountProduct(resultSet.getBoolean("discount_product"));
                product.setTotalPrice(resultSet.getDouble("total_price"));
                product.setDiscountCardPercent(resultSet.getDouble("discount_card_percent"));
                product.setTotalDiscount(resultSet.getDouble("total_discount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public boolean update(Long id, Basket newProduct) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLBasket.UPDATE.QUERY)) {
            statement.setString(1, newProduct.getNameProduct());
            statement.setDouble(2, newProduct.getPriceProduct());
            statement.setBoolean(3, newProduct.getDiscountProduct());
            statement.setDouble(4, newProduct.getQuantityProduct());
            statement.setLong(5, id);
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean insert(@NonNull final Basket basket) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement((SQLBasket.INSERT.QUERY))) {
            String nameProduct = basket.getNameProduct();
            Double priceProduct = basket.getPriceProduct();
            Boolean discountProduct = basket.getDiscountProduct();
            Double quantityProduct = basket.getQuantityProduct();
            Double totalPrice = basket.getTotalPrice();
            Double discountCardPercent = basket.getDiscountCardPercent();
            Double totalDiscount = basket.getTotalDiscount();

            statement.setString(1, nameProduct);
            statement.setDouble(2, priceProduct);
            statement.setBoolean(3, discountProduct);
            statement.setDouble(4, quantityProduct);
            statement.setDouble(5, totalPrice);
            statement.setDouble(6, discountCardPercent);
            statement.setDouble(7, totalDiscount);

            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean delete(Long id) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLBasket.DELETE.QUERY)) {
            statement.setLong(1, id);
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     SQL queries for products table in to basket.
     */
    enum SQLBasket {
        INSERT("INSERT INTO basket (id,name_product, price_product, discount_product,quantity_product,total_price,discount_card_percent,total_discount) VALUES (DEFAULT,(?), (?), (?),(?), (?), (?), (?)) RETURNING id"),
        GET("SELECT *  FROM basket WHERE id = (?)"),
        DELETE("DELETE FROM basket WHERE id = (?) RETURNING id"),
        UPDATE("UPDATE basket SET name_product = (?),price_product= (?), discount_product= (?), quantity_product=(?) WHERE id_product= (?) RETURNING id_product"),
        GET_ALL("SELECT * FROM basket");

        final String QUERY;

        SQLBasket(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
