package ru.clevertec.checkrunnerservlets.repository.impl;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import ru.clevertec.checkrunnerservlets.model.DiscountCard;
import ru.clevertec.checkrunnerservlets.repository.Repository;

import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@AllArgsConstructor
public class RepositoryDiscountCardImpl implements Repository<DiscountCard, Integer> {

    @NonNull
    private final Connection connection;

    @Override
    public List<DiscountCard> findAll() {
        List<DiscountCard> cards = new CopyOnWriteArrayList<>();
        try (Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(SQLDiscountCard.GET_ALL.QUERY);
            while (resultSet.next()) {
                cards.add(new DiscountCard(
                        resultSet.getLong("id"),
                        resultSet.getInt("number_card"),
                        resultSet.getDouble("discount_percent")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return cards;
    }

    @Override
    public DiscountCard find(@NonNull final Integer number) {
        final DiscountCard card = new DiscountCard();

        try (PreparedStatement statement = connection.prepareStatement((SQLDiscountCard.GET.QUERY))) {
            statement.setInt(1, number);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                card.setId(resultSet.getLong("id"));
                card.setNumberCard(resultSet.getInt("number_card"));
                card.setDiscountPercent(resultSet.getDouble("discount_percent"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return card;
    }

    @Override
    public boolean update(@NonNull final Integer number,@NonNull final DiscountCard newDiscountCard) {
        boolean result;

        try (PreparedStatement statement = connection.prepareStatement(SQLDiscountCard.UPDATE.QUERY)) {
            statement.setInt(1, newDiscountCard.getNumberCard());
            statement.setDouble(2, newDiscountCard.getDiscountPercent());
            statement.setInt(3, number);
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean insert(@NonNull final DiscountCard discountCard) {
        boolean result;

        try (PreparedStatement statement = connection.prepareStatement((SQLDiscountCard.INSERT.QUERY))) {
            statement.setInt(1, discountCard.getNumberCard());
            statement.setDouble(2, discountCard.getDiscountPercent());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean delete(@NonNull final Integer number) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLDiscountCard.DELETE.QUERY)) {
            statement.setInt(1, number);
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    enum SQLDiscountCard {
        INSERT("INSERT INTO discount_card (id,number_card, discount_percent) VALUES (DEFAULT,(?), (?)) RETURNING id"),
        GET("SELECT id,number_card, discount_percent FROM discount_card WHERE number_card = (?)"),
        DELETE("DELETE FROM discount_card WHERE number_card = (?)  RETURNING id"),
        UPDATE("UPDATE discount_card SET number_card = (?),discount_percent= (?) WHERE number= (?) RETURNING id"),
        GET_ALL("SELECT * FROM discount_card");

        final String QUERY;

        SQLDiscountCard(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
