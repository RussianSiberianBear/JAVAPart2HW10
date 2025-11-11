package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;


public abstract class Product implements Searchable {

    private final UUID id;
    protected String name;

    public Product(String productName) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Имя продукта не может быть пустым!");
        }
        this.id = UUID.randomUUID();
        this.name = productName.trim();
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public abstract boolean isSpecial();

    @JsonIgnore
    public String getContentType() {
        return "PRODUCT";
    }

    @JsonIgnore
    public String searchTerm() {
        return this.getContentType() + "  " + this.name;
    }

    @Override
    public boolean equals(Searchable o) {
        if (this == o) return true;
        if (o == null) return false;
        return o.getName().equals(this.getName());
    }

    @Override
    public int hashCode() {
        return 97 + Objects.hashCode(this.getName());
    }
}

