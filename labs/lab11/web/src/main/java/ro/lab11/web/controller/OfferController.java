package ro.lab11.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.lab11.core.service.OfferService;
import ro.lab11.core.tools.Logger;
import ro.lab11.web.converter.OfferConvertor;
import ro.lab11.web.dto.OffersDTO;
import ro.lab11.web.dto.OfferDTO;

@RestController
@RequestMapping(value = "/offers")
public class OfferController {
    public static final Logger logger = new Logger(OfferController.class);

    @Autowired
    private OfferService offerService;

    @Autowired
    private OfferConvertor convertor;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    String test() {
        logger.info("start");
        return "Offer controller is working!";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    OffersDTO getOffers() {
        return new OffersDTO(convertor.convertModelsToDTOs(offerService.getOffers()));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    void addOffer(@RequestBody OfferDTO offerDTO) {
        offerService.addOffer(offerDTO.getId().getAgencyId(), offerDTO.getId().getEstateId(), offerDTO.getPrice());
    }

    @RequestMapping(value = "/{agencyId}&{estateId}/update", method = RequestMethod.POST)
    void updateOffer(@PathVariable Long agencyId, @PathVariable Long estateId, @RequestBody OfferDTO offerDTO) {
        offerService.updateOffer(agencyId, estateId, offerDTO.getPrice());
    }

    @RequestMapping(value = "/{agencyId}&{estateId}/remove", method = RequestMethod.POST)
    ResponseEntity<?> removeOffer(@PathVariable Long agencyId, @PathVariable Long estateId) {
        offerService.removeOffer(agencyId, estateId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
