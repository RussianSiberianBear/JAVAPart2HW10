package org.skypro.skyshop.model.basket;


import java.util.List;
import java.util.Objects;

public class UserBasket {

    private final List<BasketItem> basketItem;
    private final int total;

    public UserBasket(List<BasketItem> item) {
        this.basketItem = item;
        this.total = basketItem.stream()
                .filter(Objects::nonNull)
                .mapToInt(obj -> obj.getProduct().getProductPrice())
                .sum();
    }

    public List<BasketItem> getBasketItem() {
        return basketItem;
    }

    public int getTotal() {
        return total;
    }
}
