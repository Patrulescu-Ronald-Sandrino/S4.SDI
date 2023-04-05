package ro.lab11.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.lab11.core.service.AgencyService;
import ro.lab11.core.tools.Logger;
import ro.lab11.web.converter.AgencyConvertor;
import ro.lab11.web.dto.AgenciesDTO;
import ro.lab11.web.dto.AgencyDTO;

@RestController
@RequestMapping(value = "/agencies")
public class AgencyController {
    public static final Logger logger = new Logger(AgencyController.class);

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private AgencyConvertor convertor;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    String test() {
        logger.info("start");
        return "Agency controller is working!";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    AgenciesDTO getAgencies() {
        return new AgenciesDTO(convertor.convertModelsToDTOs(agencyService.getAgencies()));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    void addAgency(@RequestBody AgencyDTO agencyDTO) {
        agencyService.addAgency(agencyDTO.getName(), agencyDTO.getAddress());
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    void updateAgency(@PathVariable Long id, @RequestBody AgencyDTO agencyDTO) {
        agencyService.updateAgency(id, agencyDTO.getName(), agencyDTO.getAddress());
    }

    @RequestMapping(value = "/{id}/remove", method = RequestMethod.POST)
    ResponseEntity<?> removeAgency(@PathVariable Long id) {
        agencyService.removeAgency(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
