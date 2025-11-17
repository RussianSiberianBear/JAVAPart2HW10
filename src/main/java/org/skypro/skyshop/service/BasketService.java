package org.skypro.skyshop.service;

import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {

    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public ProductBasket getProductBasket() {
        return productBasket;
    }

    public StorageService getStorageService() {
        return storageService;
    }

    public boolean addToBasket(UUID id) {
        if (storageService.getProductById(id).isEmpty()) {
            throw new NoSuchProductException("Продукт с id: " + id + " не существует!");
        }
        productBasket.add(id);
        return true;
    }

    public UserBasket getUserBasket() {

        List<BasketItem> result = productBasket.getProducts().entrySet().stream()
                .map(obj -> {
                    Optional<Product> productOpt = storageService.getProductById(obj.getKey());
                    return productOpt.map(product -> new BasketItem(product, obj.getValue()));
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        return new UserBasket(result);
    }

}
