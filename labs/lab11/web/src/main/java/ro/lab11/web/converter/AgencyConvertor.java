package ro.lab11.web.converter;

import org.springframework.stereotype.Component;
import ro.lab11.core.domain.Agency;
import ro.lab11.web.dto.AgencyDTO;

@Component
public class AgencyConvertor extends BaseEntityConverter<Long, Agency, AgencyDTO> {
    @Override
    public Agency convertDTOToModel(AgencyDTO dto) {
        return Agency.builder()
                .id(dto.getId())
                .name(dto.getName())
                .address(dto.getAddress())
                .build();
//        Agency agency =  Agency.builder()
//                .name(dto.getName())
//                .address(dto.getAddress())
//                .build();
//        agency.setId(dto.getId());
//        return agency;
    }

    @Override
    public AgencyDTO convertModelToDTO(Agency agency) {
        return AgencyDTO.builder()
                .id(agency.getId())
                .name(agency.getName())
                .address(agency.getAddress())
                .build();
//        AgencyDTO agencyDTO = AgencyDTO.builder()
//                .name(agency.getName())
//                .address(agency.getAddress())
//                .build();
//        agency.setId(agency.getId());
//        return agencyDTO;
    }
}
