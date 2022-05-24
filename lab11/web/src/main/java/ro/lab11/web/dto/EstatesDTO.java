package ro.lab11.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class EstatesDTO {
    Set<EstateDTO> estates;
}
