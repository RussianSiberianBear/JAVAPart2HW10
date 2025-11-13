package org.skypro.skyshop.controller;

import org.skypro.skyshop.error.ShopError;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<String> noSuchProduct(NoSuchProductException e) {
        return ResponseEntity.status(ShopError.getCode()).body(ShopError.getMessage());
    }
}
