package ro.lab11.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.lab11.core.service.EstateService;
import ro.lab11.core.tools.Logger;
import ro.lab11.web.converter.EstateConvertor;
import ro.lab11.web.dto.EstatesDTO;
import ro.lab11.web.dto.EstateDTO;

@RestController
@RequestMapping(value = "/estates")
public class EstateController {
    public static final Logger logger = new Logger(EstateController.class);

    @Autowired
    private EstateService estateService;

    @Autowired
    private EstateConvertor convertor;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    String test() {
        logger.info("start");
        return "Estate controller is working!";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    EstatesDTO getEstates() {
        return new EstatesDTO(convertor.convertModelsToDTOs(estateService.getEstates()));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    void addEstate(@RequestBody EstateDTO estateDTO) {
        estateService.addEstate(estateDTO.getAddress(), estateDTO.getSurface());
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    void updateEstate(@PathVariable Long id, @RequestBody EstateDTO estateDTO) {
        estateService.updateEstate(id, estateDTO.getAddress(), estateDTO.getSurface());
    }

    @RequestMapping(value = "/{id}/remove", method = RequestMethod.POST)
    ResponseEntity<?> removeEstate(@PathVariable Long id) {
        estateService.removeEstate(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
