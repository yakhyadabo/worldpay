package org.worldpay.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Yakhya DABO on 16/10/16.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OfferNotFoundException extends RuntimeException {

    public OfferNotFoundException(Integer reference) {
      super("could not find Offer with ref '" + reference + "'.");
    }
}
