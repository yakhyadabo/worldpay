package org.worldpay.shop.enums;

/**
 * Created by Yakhya Dabo on 16/10/16.
 */
public enum Currency {
  USD("USD", "US Dollar"),
  EUR("EUR","Euro"),
  GBP("GBP", "Pound Sterling"),
  JPY("JPY","Japanese Yen"),
  CHF ("CHF", "Swiss Franc"),
  MAD("MAD","Moroccan Dirham");

  private String id;
  private String name;

  private Currency(String id, String name) {
    this.id=id;
    this.name =name;
  }

}

