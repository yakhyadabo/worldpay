package org.worldpay.shop.domain;

import lombok.Getter;
import org.worldpay.shop.enums.Currency;

import java.math.BigDecimal;

/**
 * Created by Yakhya Dabo on 16/10/16.
 */
public class Price {
  @Getter
  private BigDecimal amount;

  @Getter
  private Currency currency;

  // Constructor required for Json parsing
  public Price() {}

  public Price(Double amount, Currency currency) {
    this.amount = BigDecimal.valueOf(amount);
    this.currency = currency;
  }

  public static Price of(Double amount){
    return new Price(amount, Currency.GBP);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Price price = (Price) o;

    if (!amount.equals(price.amount)) return false;
    return currency == price.currency;

  }

  @Override
  public int hashCode() {
    int result = amount.hashCode();
    result = 31 * result + currency.hashCode();
    return result;
  }
}
