package ro.lab11.web.converter;

import org.springframework.stereotype.Component;
import ro.lab11.core.domain.Estate;
import ro.lab11.web.dto.EstateDTO;

@Component
public class EstateConvertor extends BaseEntityConverter<Long, Estate, EstateDTO> {
    @Override
    public Estate convertDTOToModel(EstateDTO dto) {
        return Estate.builder()
                .id(dto.getId())
                .address(dto.getAddress())
                .surface(dto.getSurface())
                .build();
    }

    @Override
    public EstateDTO convertModelToDTO(Estate estate) {
        return EstateDTO.builder()
                .id(estate.getId())
                .address(estate.getAddress())
                .surface(estate.getSurface())
                .build();
    }
}
