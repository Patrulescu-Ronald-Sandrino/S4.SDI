package ro.lab11.web.converter;

import ro.lab11.core.domain.BaseEntity;
import ro.lab11.web.dto.BaseEntityDTO;

import java.io.Serializable;
public interface Converter<ID extends Serializable, Model extends BaseEntity<ID>, DTO extends BaseEntityDTO<ID>> {
    Model convertDTOToModel(DTO dto);
    DTO convertModelToDTO(Model model);
}

/*
from builder to constructor (doesn't work, because the lombok constructor doesn't include super class fields

// initial regex: "return (\p{Alpha}+).builder\(\)(\n +\.\p{Alpha}+\((\p{Alpha}+\.\p{Alpha}+\(\))\))+\n +.build\(\)"


replace: "\n +\.\p{Alpha}+\((\p{Alpha}+\.\p{Alpha}+\(\))\)"
with: "$1, "

replace: "(\p{Alpha}+).builder\(\)"
with: "new $1("

replace: ", \n +\.build\(\)"
with: ")"

*/