package ro.lab11.web.converter;

import ro.lab11.core.domain.BaseEntity;
import ro.lab11.web.dto.BaseEntityDTO;

import java.io.Serializable;

public interface Converter<ID extends Serializable, Model extends BaseEntity<ID>, DTO extends BaseEntityDTO<ID>> {
    Model convertDTOToModel(DTO dto);
    DTO convertModelToDTO(Model model);
}
