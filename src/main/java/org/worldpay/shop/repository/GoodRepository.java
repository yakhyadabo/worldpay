package org.worldpay.shop.repository;

import org.springframework.stereotype.Repository;
import org.worldpay.shop.domain.Good;
import org.worldpay.shop.domain.Price;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Yakhya DABO on 16/10/16.
 */

@Repository
public class GoodRepository {
  private static Map<Integer, Good> goodMap = new HashMap<>();

  static {
    insertGoods();
  }

  public Optional<Good> findByReference(Integer reference){
    return Optional.ofNullable(goodMap.get(reference));
  }

  public void save(Good good){
    goodMap.put(good.getReference(),good);
  }

  public List<Good> findAll(){
    return goodMap.entrySet()
        .stream()
        .map(good -> good.getValue())
        .collect(Collectors.toList());
  }

  public static void clear(){
      goodMap.clear();
  }

  private static void insertGoods() {
    Good good0 = new Good(0, "Good 000", Price.of(10.00));
    Good good1 = new Good(1, "Good 111", Price.of(11.11));
    Good good2 = new Good(2, "Good 222", Price.of(12.10));
    Good good3 = new Good(3, "Good 333", Price.of(13.00));
    Good good4 = new Good(4, "Good 444", Price.of(14.50));
    Good good5 = new Good(5, "Good 555", Price.of(15.10));

    goodMap.put(good0.getReference(), good0);
    goodMap.put(good1.getReference(), good1);
    goodMap.put(good2.getReference(), good2);
    goodMap.put(good3.getReference(), good3);
    goodMap.put(good4.getReference(), good4);
    goodMap.put(good5.getReference(), good5);
  }
}