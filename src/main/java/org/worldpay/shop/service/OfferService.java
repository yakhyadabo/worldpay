package org.worldpay.shop.service;

import org.worldpay.shop.domain.Offer;

import java.util.List;
import java.util.Optional;

/**
 * Created by ydabo on 28/10/16.
 */
public interface OfferService {
  Optional<Offer> getByReference(Integer reference);
  Offer add(Offer offer);
  List<Offer> getAll();
}
