package org.worldpay.shop.repository;

import org.junit.Before;
import org.junit.Test;
import org.worldpay.shop.domain.Offer;
import org.worldpay.shop.domain.Price;
import org.worldpay.shop.enums.Currency;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Yakhya Dabo on 16/10/16.
 */
public class OfferRepositoryTest {

  private OfferRepository offerRepository = new OfferRepository();

  @Before
  public void setup() throws Exception {
    OfferRepository.clear();
  }

  @Test
  public void should_not_return_non_existing_offer(){
    Optional<Offer> offer = offerRepository.findByReference(7777);

    assertThat(offer).isEqualTo(Optional.empty());
  }

  @Test
  public void should_add_new_offer(){
    offerRepository.save(new Offer(7777, "Offer 777", Price.of(777.00)));
    Offer offer = offerRepository.findByReference(7777).get();

    assertThat(offer).isNotNull();
    assertThat(offer.getReference()).isEqualTo(7777);
    assertThat(offer.getDescription()).isEqualTo("Offer 777");
    assertThat(offer.getPrice().getAmount()).isEqualTo(BigDecimal.valueOf(777.00));
    assertThat(offer.getPrice().getCurrency()).isEqualTo(Currency.GBP);
  }

  @Test
  public void should_retrieve_all_offers(){
    offerRepository.save(new Offer(7777, "Offer 777", Price.of(777.00)));
    offerRepository.save(new Offer(8888, "Offer 888", Price.of(888.00)));
    offerRepository.save(new Offer(9999, "Offer 999", Price.of(999.00)));

    Offer offer0 = offerRepository.findByReference(7777).get();
    Offer offer1 = offerRepository.findByReference(8888).get();
    Offer offer2 = offerRepository.findByReference(9999).get();

    assertThat(offerRepository.findAll()).contains(offer0, offer1, offer2);
  }
}