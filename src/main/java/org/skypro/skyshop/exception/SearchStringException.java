package org.skypro.skyshop.exception;

import org.skypro.skyshop.error.ShopError;

public class SearchStringException extends RuntimeException {
    public SearchStringException(String message) {
        ShopError shopError = new ShopError(400, message);
    }
}
