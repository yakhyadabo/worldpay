package org.worldpay.shop.service.impl;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.worldpay.shop.repository.GoodRepository;
import org.worldpay.shop.service.OfferService;

/**
 * Created by ydabo on 29/10/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class OfferServiceImplTest {

  @Mock
  private GoodRepository goodRepository;

  @InjectMocks
  private OfferService offerService;

  public void setUp(){

  }


}