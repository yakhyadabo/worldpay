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

}
