package org.worldpay.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Yakhya Dabo on 16/10/16.
 */
@AllArgsConstructor
public class Offer {
  @Getter
  private Integer reference;

  @Getter
  private String description;

  @Getter
  private Price price;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Offer offer = (Offer) o;

    return reference != null ? reference.equals(offer.reference) : offer.reference == null;
  }

  @Override
  public int hashCode() {
    return reference != null ? reference.hashCode() : 0;
  }
}