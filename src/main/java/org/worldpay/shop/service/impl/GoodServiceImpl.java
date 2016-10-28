package org.worldpay.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.worldpay.shop.domain.Good;
import org.worldpay.shop.repository.GoodRepository;
import org.worldpay.shop.service.GoodService;

import java.util.List;
import java.util.Optional;

/**
 * Created by ydabo on 29/10/16.
 */
@Service(value = "goodService")
public class GoodServiceImpl implements GoodService {

  @Autowired
  private GoodRepository goodRepository;

  @Override
  public Optional<Good> get(Integer reference) {
    return goodRepository.findByReference(reference);
  }

  @Override
  public void add(Good good) {
    goodRepository.save(good);
  }

  @Override
  public List<Good> getAll() {
    return goodRepository.findAll();
  }
}
