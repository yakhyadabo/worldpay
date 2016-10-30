package org.worldpay.shop.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.worldpay.shop.domain.Offer;
import org.worldpay.shop.domain.Price;
import org.worldpay.shop.repository.OfferRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ydabo on 29/10/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class OfferServiceImplTest {

  @Mock
  private OfferRepository offerRepository;

  @InjectMocks
  private OfferServiceImpl offerService;

  @Before
  public void setUp(){
    OfferRepository.clear();
  }

  @Test
  public void should_return_empty_when_offer_does_not_exist(){
    when(offerRepository.findByReference(10)).thenReturn(Optional.empty());

    Optional<Offer> offer = offerService.getByReference(10);

    assertThat(offer.isPresent()).isFalse();
  }

  @Test
  public void should_return_offer_when_it_exists(){
    when(offerRepository.findByReference(10)).thenReturn(newOffer(10,"Offer 10", Price.of(12.00)));

    Optional<Offer> offer = offerService.getByReference(10);

    assertThat(offer.isPresent()).isTrue();
    assertThat(offer.get().getReference()).isEqualTo(10);
    assertThat(offer.get().getDescription()).isEqualTo("Offer 10");
    assertThat(offer.get().getPrice()).isEqualTo(Price.of(12.0));
  }

  @Test
  public void should_add_offer_to_repository(){
    Offer offer = newOffer(10,"Offer 10", Price.of(100.00)).get();

    offerService.add(offer);

    verify(offerRepository, atLeastOnce()).save(offer);
  }

  @Test
  public void should_get_all_offers_from_repository(){
    offerService.getAll();

    verify(offerRepository, atLeastOnce()).findAll();
  }

  private static Optional<Offer> newOffer(Integer reference, String description, Price price){
    return Optional.of(new Offer(reference,description,price));
  }
}