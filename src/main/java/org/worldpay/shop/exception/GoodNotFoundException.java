package org.worldpay.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Yakhya DABO on 16/10/16.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GoodNotFoundException extends RuntimeException {

    public GoodNotFoundException(Integer reference) {
      super("could not find Good with ref '" + reference + "'.");
    }
}
