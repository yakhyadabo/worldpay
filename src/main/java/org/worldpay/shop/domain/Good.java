package org.worldpay.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * Created by Yakhya Dabo on 16/10/16.
 */
@AllArgsConstructor
public class Good {
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

    Good good = (Good) o;

    return reference != null ? reference.equals(good.reference) : good.reference == null;
  }

  @Override
  public int hashCode() {
    return reference != null ? reference.hashCode() : 0;
  }
}