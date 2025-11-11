package org.skypro.skyshop.model.product;

public class FixPriceProduct extends Product {

    private static final int FIX_PRICE = 120;

    public FixPriceProduct(String productName) {
        super(productName);
    }

    @Override
    public int getProductPrice() {
        return FIX_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return this.name + ": Фиксированная цена " + this.getProductPrice();
    }
}