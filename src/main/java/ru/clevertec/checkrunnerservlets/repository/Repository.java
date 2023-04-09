package ru.clevertec.checkrunnerservlets.repository;

import java.util.List;

/**
 Interface for defining entity data storage functions

 @param <T>
 */
public interface Repository<Entity,Key> {

    //Getting a List of Entities
    List<Entity> findAll();

    //Entity Search
    Entity find(Key key);

    //Entity update
    boolean update(Key key, Entity entity);

    //Adding entity - returns the ID of the added entity
    boolean insert(Entity entity);

    //Deleting an entity
    boolean delete(Key key);
}
