package org.worldpay.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.worldpay.shop.domain.Good;
import org.worldpay.shop.exception.GoodNotFoundException;
import org.worldpay.shop.repository.GoodRepository;
import org.worldpay.shop.service.GoodService;

import java.util.List;

/**
 * Created by Yakhya Dabo on 16/10/16.
 */

@RestController
public class WorldPayController {

  @Autowired
  private GoodService goodService;

  @RequestMapping(value = "/goods", method = RequestMethod.GET)
  public ResponseEntity<List<Good>> getAllGoods() {
    List<Good> goods = goodService.getAll();
    return new ResponseEntity<>(goods, HttpStatus.OK);
  }

  @RequestMapping(value = "/goods", method = RequestMethod.POST)
  public ResponseEntity<Good> addGood(@RequestBody Good good) {
    goodService.add(good);
    return new ResponseEntity<>(good, HttpStatus.OK);
  }

  @RequestMapping(value = "/goods/{reference}", method = RequestMethod.GET)
  public ResponseEntity<Good> getGood(@PathVariable Integer reference) {
    return goodService.get(reference)
        .map(good -> new ResponseEntity<>(good, HttpStatus.OK))
        .orElseThrow(() -> new GoodNotFoundException(reference));
  }
}