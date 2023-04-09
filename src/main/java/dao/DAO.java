package dao;

public interface DAO<Entity, Key> {

    boolean create(Entity model);

    Entity read(Key key);

    boolean update(Key key, Entity newModel);

    boolean delete(Key key);
}
