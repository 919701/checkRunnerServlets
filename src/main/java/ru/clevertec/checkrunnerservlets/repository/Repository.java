package ru.clevertec.checkrunnerservlets.repository;

import java.util.List;

/**
 Interface for defining entity data storage functions

 @param <T>
 */
public interface Repository<T> {

    //Getting a List of Entities
    List<T> findAll();

    //Entity Search
    T find(int id);

    //Entity update
    T update(T entity);

    //Adding entity - returns the ID of the added entity
    T insert(T entity);

    //Deleting an entity
    T delete(int id, T entity);
}
