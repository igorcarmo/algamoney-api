package com.igorac.algamoney.api.model.mapper;

import java.util.List;
import java.util.Optional;

/**
 * Interface to map Entities and DTOs. Extend to specialize the mapping targets and annotate with @Mapper annotation,
 * adding the property componentModel set to "spring";
 *
 * @param <E> Entity class
 * @param <D> DTO class
 */
public interface ModelMapper<E, D> {

    D entityToDto(E entity);
    E dtoToEntity(D dto);
    List<D> entitiesToDtos(List<E> entities);
    List<E> dtosToEntities(List<D> dtos);

    default Optional<D> entityToDto(Optional<E> entity) {
        if (entity.isEmpty()) return Optional.empty();
        return Optional.ofNullable(entityToDto(entity.get()));
    }
}
