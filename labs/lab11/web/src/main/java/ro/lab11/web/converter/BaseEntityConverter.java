package ro.lab11.web.converter;

import ro.lab11.core.domain.BaseEntity;
import ro.lab11.web.dto.BaseEntityDTO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseEntityConverter<ID extends Serializable, Model extends BaseEntity<ID>, DTO extends BaseEntityDTO<ID>> implements Converter<ID, Model, DTO> {
    public Set<DTO> convertModelsToDTOs(Collection<Model> models) {
        return models.stream().map(this::convertModelToDTO).collect(Collectors.toSet());
    }
}
