package org.worldpay.shop.service;

import org.worldpay.shop.domain.Good;

import java.util.List;
import java.util.Optional;

/**
 * Created by ydabo on 28/10/16.
 */
public interface OfferService {
  Optional<Good> get(Integer reference);
  void add(Good good);
  List<Good> getAll();
}
