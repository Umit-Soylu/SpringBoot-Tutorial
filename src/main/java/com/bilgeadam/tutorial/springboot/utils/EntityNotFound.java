package com.bilgeadam.tutorial.springboot.utils;

import javax.persistence.EntityNotFoundException;

public class EntityNotFound extends EntityNotFoundException {

    /**
     * This is called when a specific entity is not found.
     * @param entity Class of entity
     * @param id     Id of the entity
     * @param <T>    Type of entity
     */
    public <T> EntityNotFound(Class<T> entity, Long id) {
        super(entity.getSimpleName() + " with id '" + id + "' not found.");
    }

    /**
     * This is called when no entity is found
     * @param entity Class of entity
     * @param <T>    Type declaration
     */
    public <T> EntityNotFound(Class<T> entity) {
        super("No " + entity.getSimpleName() + " found.");
    }
}
