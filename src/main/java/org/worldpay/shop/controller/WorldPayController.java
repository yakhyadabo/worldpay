package org.worldpay.shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.worldpay.shop.domain.Good;
import org.worldpay.shop.exception.GoodNotFoundException;
import org.worldpay.shop.repository.GoodRepository;

import java.util.List;

/**
 * Created by Yakhya Dabo on 16/10/16.
 */

@RestController
public class WorldPayController {

  @RequestMapping(value = "/goods", method = RequestMethod.GET)
  public ResponseEntity<List<Good>> getAllGoods() {
    List<Good> goods = GoodRepository.getAll();
    return new ResponseEntity<>(goods, HttpStatus.OK);
  }

  @RequestMapping(value = "/goods", method = RequestMethod.POST)
  public ResponseEntity<Good> addGood(@RequestBody Good good) {
    GoodRepository.add(good);
    return new ResponseEntity<>(good, HttpStatus.OK);
  }

  @RequestMapping(value = "/goods/{reference}", method = RequestMethod.GET)
  public ResponseEntity<Good> getGood(@PathVariable Integer reference) {
    return GoodRepository.get(reference)
        .map(good -> new ResponseEntity<>(good, HttpStatus.OK))
        .orElseThrow(() -> new GoodNotFoundException(reference));
  }
}