package org.skypro.skyshop.service;

import org.skypro.skyshop.exception.SearchStringException;
import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String searchStr) {

        if (searchStr == null || searchStr.isBlank()) {
            throw new SearchStringException("Строка поиска не может быть пустой!");
        }

        String finalSearchStr = searchStr.toLowerCase();

        return storageService.getSearchableStorage().stream()
                .filter(Objects::nonNull)
                .filter(obj -> obj.searchTerm() != null)
                .filter(obj -> obj.searchTerm().toLowerCase().contains(finalSearchStr))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }

}
