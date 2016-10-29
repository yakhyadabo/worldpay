package org.worldpay.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.worldpay.shop.domain.Offer;
import org.worldpay.shop.repository.OfferRepository;
import org.worldpay.shop.service.OfferService;

import java.util.List;
import java.util.Optional;

/**
 * Created by ydabo on 29/10/16.
 */
@Service(value = "offerService")
public class OfferServiceImpl implements OfferService {

  @Autowired
  private OfferRepository offerRepository;

  @Override
  public Optional<Offer> get(Integer reference) {
    return offerRepository.findByReference(reference);
  }

  @Override
  public void add(Offer offer) {
    offerRepository.save(offer);
  }

  @Override
  public List<Offer> getAll() {
    return offerRepository.findAll();
  }
}
