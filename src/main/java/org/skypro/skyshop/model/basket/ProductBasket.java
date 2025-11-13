package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

@SessionScope
@Component
public class ProductBasket {

    private final Map<UUID, Integer> products = new TreeMap<>();

    public ProductBasket() {
    }

    public void add(UUID id) {
        if (id == null || id.toString().isEmpty()) {
            throw new IllegalArgumentException("Добавляемый в корзину продукт должен иметь UUID!");
        }
        int cntProduct = products.getOrDefault(id, 0);
        products.put(id, ++cntProduct);
    }

    public Map<UUID, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }

}
