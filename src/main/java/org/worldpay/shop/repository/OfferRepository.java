package org.worldpay.shop.repository;

import org.springframework.stereotype.Repository;
import org.worldpay.shop.domain.Offer;
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
public class OfferRepository {
  private static Map<Integer, Offer> offerHashMap = new HashMap<>();

  static {
    insertGoods();
  }

  public Optional<Offer> findByReference(Integer reference){
    return Optional.ofNullable(offerHashMap.get(reference));
  }

  public Offer save(Offer offer){
    offerHashMap.put(offer.getReference(), offer);
    return offerHashMap.get(offer.getReference());
  }

  public List<Offer> findAll(){
    return offerHashMap.entrySet()
        .stream()
        .map(good -> good.getValue())
        .collect(Collectors.toList());
  }

  public static void clear(){
      offerHashMap.clear();
  }

  private static void insertGoods() {
    Offer offer0 = new Offer(0, "Offer 000", Price.of(10.00));
    Offer offer1 = new Offer(1, "Offer 111", Price.of(11.11));
    Offer offer2 = new Offer(2, "Offer 222", Price.of(12.10));
    Offer offer3 = new Offer(3, "Offer 333", Price.of(13.00));
    Offer offer4 = new Offer(4, "Offer 444", Price.of(14.50));
    Offer offer5 = new Offer(5, "Offer 555", Price.of(15.10));

    offerHashMap.put(offer0.getReference(), offer0);
    offerHashMap.put(offer1.getReference(), offer1);
    offerHashMap.put(offer2.getReference(), offer2);
    offerHashMap.put(offer3.getReference(), offer3);
    offerHashMap.put(offer4.getReference(), offer4);
    offerHashMap.put(offer5.getReference(), offer5);
  }
}