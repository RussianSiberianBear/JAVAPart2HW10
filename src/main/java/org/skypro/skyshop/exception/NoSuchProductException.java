package org.skypro.skyshop.exception;

import org.skypro.skyshop.error.ShopError;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException(String message) {
        ShopError shopError = new ShopError(404, message);
    }
}
