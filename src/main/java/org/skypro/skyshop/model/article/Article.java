package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {

    private final UUID id;
    private final String name;
    private final String content;

    @Override
    public UUID getId() {
        return id;
    }

    public Article(String name, String content) {
        if (name == null || name.isBlank() || content == null || content.isBlank()) {
            throw new IllegalArgumentException("Название и/или содержание статьи не может быть пустым!");
        }
        this.id = UUID.randomUUID();
        this.name = name;
        this.content = content;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getProductPrice() {
        return 0;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return "\n" + this.name + "\n" + this.content;
    }

    @JsonIgnore
    public String getContentType() {
        return "ARTICLE";
    }

    @JsonIgnore
    public String searchTerm() {
        return this.toString();
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
