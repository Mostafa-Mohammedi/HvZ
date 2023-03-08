package no.noroff.HvZ.services;

import java.util.Collection;

public interface CrudService <T, Integer>{
    /**
     * Executes a query to find a specific entity T by id.
     *
     * @param id The id of the entity
     * @return T An entity from the database
     */
    T findById(Integer id);

    /**
     * Queries the database for all entities T in a table.
     *
     * @return Collection<T> A list containing all entities in a given table
     */
    Collection<T> findAll();

    /**
     * Executes a query to add an entity T to its corresponding table.
     *
     * @param entity The entity to be added
     * @return T The entity which was added
     */
    T add(T entity);

    /**
     * Executes a query to update an existing entity T in a table.
     *
     * @param entity The entity to be updated
     * @return T The object which was updated
     */
    T update(T entity);

    /**
     * Executes a query to delete an entity T from a table.
     *
     * @param id The id of the entity to be deleted
     */
    void deleteById(Integer id);

    /**
     * Executes a query to delete an entity T from a table.
     *
     * @param id The id of the entity check if exists in table
     * @return boolean True if entity exists within table, false if not
     */
    boolean exists(Integer id);


}
