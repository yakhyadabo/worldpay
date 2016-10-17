package org.worldpay.shop.repository;

import org.junit.Before;
import org.junit.Test;
import org.worldpay.shop.domain.Good;
import org.worldpay.shop.domain.Price;
import org.worldpay.shop.enums.Currency;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Yakhya Dabo on 16/10/16.
 */
public class GoodRepositoryTest {

  @Before
  public void setup() throws Exception {
    GoodRepository.clear();
  }

  @Test
  public void should_not_return_non_existing_good(){
    Optional<Good> good = GoodRepository.get(7777);

    assertThat(good).isEqualTo(Optional.empty());
  }

  @Test
  public void should_add_new_good(){
    GoodRepository.add(new Good(7777, "Good 777", Price.of(777.00)));
    Good good = GoodRepository.get(7777).get();

    assertThat(good).isNotNull();
    assertThat(good.getReference()).isEqualTo(7777);
    assertThat(good.getDescription()).isEqualTo("Good 777");
    assertThat(good.getPrice().getAmount()).isEqualTo(BigDecimal.valueOf(777.00));
    assertThat(good.getPrice().getCurrency()).isEqualTo(Currency.GBP);
  }

  @Test
  public void should_retrieve_all_goods(){
    GoodRepository.add(new Good(7777, "Good 777", Price.of(777.00)));
    GoodRepository.add(new Good(8888, "Good 888", Price.of(888.00)));
    GoodRepository.add(new Good(9999, "Good 999", Price.of(999.00)));

    Good good0 = GoodRepository.get(7777).get();
    Good good1 = GoodRepository.get(8888).get();
    Good good2 = GoodRepository.get(9999).get();

    assertThat(GoodRepository.getAll()).contains(good0,good1,good2);
  }
}