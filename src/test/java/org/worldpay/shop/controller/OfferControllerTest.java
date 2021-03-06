package org.worldpay.shop.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.worldpay.shop.WorldPayStartup;
import org.worldpay.shop.domain.Offer;
import org.worldpay.shop.domain.Price;
import org.worldpay.shop.repository.OfferRepository;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by ydabo on 16/10/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WorldPayStartup.class)
@WebAppConfiguration
public class OfferControllerTest {
  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(),
      Charset.forName("utf8"));

  private MockMvc mockMvc;
  private HttpMessageConverter mappingJackson2HttpMessageConverter;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private OfferRepository offerRepository;

  @Autowired
  void setConverters(HttpMessageConverter<?>[] converters) {

    this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
        hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

  }

  @Before
  public void setup() throws Exception {
    this.mockMvc = webAppContextSetup(webApplicationContext).build();
    OfferRepository.clear();
  }

  @Test
  public void should_successfully_create_new_offer() throws Exception {
    mockMvc.perform(put("/offers")
        .content(this.json(new Offer(0,"Offer 000", Price.of(12.00))))
        .contentType(contentType))
        .andExpect(status().isOk());
  }

  @Test
  public void should_fail_with_exception_when_offer_does_not_exist() throws Exception {
    mockMvc.perform(get("/offers/9999"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void should_return_offer_when_it_exists() throws Exception {
    offerRepository.save(new Offer(9999, "Offer 999", Price.of(1000.00)));

    mockMvc.perform(get("/offers/9999"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.reference", is(9999)))
        .andExpect(jsonPath("$.description", is("Offer 999")))
        .andExpect(jsonPath("$.price.amount", is(1000.00)))
        .andExpect(jsonPath("$.price.currency", is("GBP")));
  }

  @Test
  public void should_return_all_offers() throws Exception {
    offerRepository.save(new Offer(7777, "Offer 777", Price.of(777.00)));
    offerRepository.save(new Offer(8888, "Offer 888", Price.of(888.00)));
    offerRepository.save(new Offer(9999, "Offer 999", Price.of(999.00)));

    mockMvc.perform(get("/offers"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].reference", is(7777)))
        .andExpect(jsonPath("$[0].description", is("Offer 777")))
        .andExpect(jsonPath("$[0].price.amount", is(777.00)))
        .andExpect(jsonPath("$[0].price.currency", is("GBP")))
        .andExpect(jsonPath("$[1].reference", is(8888)))
        .andExpect(jsonPath("$[1].description", is("Offer 888")))
        .andExpect(jsonPath("$[1].price.amount", is(888.00)))
        .andExpect(jsonPath("$[1].price.currency", is("GBP")))
        .andExpect(jsonPath("$[2].reference", is(9999)))
        .andExpect(jsonPath("$[2].description", is("Offer 999")))
        .andExpect(jsonPath("$[2].price.amount", is(999.00)))
        .andExpect(jsonPath("$[2].price.currency", is("GBP")));
  }

  protected String json(Object o) throws IOException {
    MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
    this.mappingJackson2HttpMessageConverter.write(
        o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
    return mockHttpOutputMessage.getBodyAsString();
  }
}