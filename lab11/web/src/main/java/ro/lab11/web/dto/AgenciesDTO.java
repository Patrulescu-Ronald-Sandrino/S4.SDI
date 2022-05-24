package ro.lab11.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class AgenciesDTO {
    Set<AgencyDTO> agencies;
}
