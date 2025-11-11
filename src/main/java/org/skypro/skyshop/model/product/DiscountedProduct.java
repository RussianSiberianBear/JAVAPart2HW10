package org.skypro.skyshop.model.product;

public class DiscountedProduct extends Product {
    private final int basePrice;
    private int discount;

    public DiscountedProduct(String productName, int basePrice, int discount) {
        super(productName);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Базовая цена должна быть больше 0!");
        }
        if (!(this.discount >= 0 && this.discount <= 100)) {
            throw new IllegalArgumentException("Скидка должна быть в пределах от 0% до 100% !");
        }
        this.basePrice = basePrice;
        this.discount = discount;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public int getProductPrice() {
        return (this.basePrice - this.basePrice * this.discount / 100);
    }

    @Override
    public String toString() {
        return this.name + ": " + this.getProductPrice() + " (скидка " + this.discount + "%)";
    }
}
