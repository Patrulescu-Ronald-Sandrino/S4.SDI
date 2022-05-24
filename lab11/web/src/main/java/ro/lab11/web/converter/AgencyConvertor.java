package ro.lab11.web.converter;

import ro.lab11.core.domain.Agency;
import ro.lab11.web.dto.AgencyDTO;

public class AgencyConvertor extends BaseEntityConverter<Long, Agency, AgencyDTO> {
    @Override
    public Agency convertDTOToModel(AgencyDTO dto) {
        return Agency.builder()
                .id(dto.getId())
                .name(dto.getName())
                .address(dto.getAddress())
                .build();
    }

    @Override
    public AgencyDTO convertModelToDTO(Agency agency) {
        return AgencyDTO.builder()
                .id(agency.getId())
                .name(agency.getName())
                .address(agency.getAddress())
                .build();
    }
}
