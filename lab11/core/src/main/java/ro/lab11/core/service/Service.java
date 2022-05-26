package ro.lab11.core.service;

public interface Service {
    default String entityWithId(Object id) {
        return "entity with id %s".formatted(id);
    }

    default String entityWithIdNotFound(Object id) {
        return "%s not found".formatted(entityWithId(id));
    }

    default String entityWithIdAdded(Object id) {
        return "added %s".formatted(entityWithId(id));
    }

    default String entityWithIdUpdated(Object id) {
        return "updated %s".formatted(entityWithId(id));
    }

    default String entityWithIdRemoved(Object id) {
        return "removed %s".formatted(entityWithId(id));
    }
    default String entityWithIdExists(Object id) {
        return  "%s exists".formatted(entityWithId(id));
    }
}
