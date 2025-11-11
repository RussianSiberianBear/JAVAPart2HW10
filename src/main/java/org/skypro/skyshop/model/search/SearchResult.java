package org.skypro.skyshop.model.search;

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
}
