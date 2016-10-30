package org.worldpay.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.worldpay.shop.domain.Offer;
import org.worldpay.shop.exception.OfferNotFoundException;
import org.worldpay.shop.service.OfferService;

import java.util.List;

/**
 * Created by Yakhya Dabo on 16/10/16.
 */

@RestController
public class OfferController {

  @Autowired
  private OfferService offerService;

  @RequestMapping(value = "/offers", method = RequestMethod.GET)
  public ResponseEntity<List<Offer>> getAllOffers() {
    List<Offer> offers = offerService.getAll();
    return new ResponseEntity<>(offers, HttpStatus.OK);
  }

  @RequestMapping(value = "/offers", method = RequestMethod.PUT)
  public ResponseEntity<Offer> addOffer(@RequestBody Offer offer) {
    Offer savedOffer =  offerService.add(offer);
    return new ResponseEntity<>(savedOffer, HttpStatus.OK);
  }

  @RequestMapping(value = "/offers/{reference}", method = RequestMethod.GET)
  public ResponseEntity<Offer> getOffer(@PathVariable Integer reference) {
    return offerService.getByReference(reference)
        .map(offer -> new ResponseEntity<>(offer, HttpStatus.OK))
        .orElseThrow(() -> new OfferNotFoundException(reference));
  }
}