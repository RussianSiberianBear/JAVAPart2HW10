package org.skypro.skyshop.model.search;

import java.util.Objects;
import java.util.UUID;

public class SearchResult {

    private final UUID id;
    private final String name;
    private final String contentType;

    public static SearchResult fromSearchable(Searchable obj) {
        return new SearchResult(obj.getId(), obj.getName(), obj.getContentType());
    }

    public SearchResult(UUID id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResult that = (SearchResult) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(contentType, that.contentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, contentType);
    }
}
