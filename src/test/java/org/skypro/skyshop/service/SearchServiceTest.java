package org.skypro.skyshop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.SearchStringException;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @ParameterizedTest
    @NullAndEmptySource
    public void whenIsEmptyOrBlankSearchString(String searchStr) {

        // Ожидаемый результат
        String waitResult = "SearchStringException";
        // Выполняем тест
        // Должно быть выброшено исключение при вызове searchService.search
        Exception exception = Assertions.assertThrows(SearchStringException.class, () -> searchService.search(searchStr));
        // Проверяем результат
        Assertions.assertEquals(waitResult, exception.getClass().getSimpleName());
    }

    @ParameterizedTest
    @ValueSource(strings = "абракадабра")
    public void whenIsEmptyStorageService(String searchStr) {

        // Выполняем тест
        Collection<SearchResult> result = searchService.search(searchStr);
        // Проверяем результат
        Assertions.assertEquals(Collections.emptyList(), result);
    }

    @ParameterizedTest
    @ValueSource(strings = "абракадабра")
    public void whenStorageIsNotEmptyButIsNoSuitableProduct(String searchStr) {

        // Готовим данные для storage
        Article[] articleArr = new Article[]{
                new Article("Новости", "Новости дня."),
                new Article("Корм для животных", "В данной статье рассказывается о видах кормов для домашних животных-кошек и собак."),
                new Article("Домашние питомцы", "Статья о кошках и собаках. Кошки и собаки давние домашние питомцы."),
                new Article("Вакансии", "Свежие вакансии на сегодня."),
                new Article("Женские товары", "Косметика, духи и другие штучки для женщин.")
        };
        Collection<Searchable> storageData = Arrays.asList(articleArr);

        // Настраиваем mocks
        when(storageService.getSearchableStorage()).thenReturn(storageData);

        // Выполняем тест
        Collection<SearchResult> result = searchService.search(searchStr);

        // Проверяем результат
        Assertions.assertEquals(Collections.emptyList(), result);
    }

    @ParameterizedTest
    @ValueSource(strings = "кош")
    public void whenStorageIsNotEmptyAndIsExistsSuitableProduct(String searchStr) {

        // Готовим данные для storage
        Article[] articleArr = new Article[]{
                new Article("Новости", "Новости дня."),
                new Article("Корм для животных", "В данной статье рассказывается о видах кормов для домашних животных-кошек и собак."),
                new Article("Домашние питомцы", "Статья о кошках и собаках. Кошки и собаки давние домашние питомцы."),
                new Article("Вакансии", "Свежие вакансии на сегодня."),
                new Article("Женские товары", "Косметика, духи и другие штучки для женщин.")
        };
        Collection<Searchable> storageData = Arrays.asList(articleArr);

        // Настраиваем mocks
        when(storageService.getSearchableStorage()).thenReturn(storageData);

        // Готовим данные, которые должны получить
        Collection<SearchResult> waitResult = new ArrayList<>();
        waitResult.add(new SearchResult(UUID.randomUUID(), "Корм для животных", "ARTICLE"));
        waitResult.add(new SearchResult(UUID.randomUUID(), "Домашние питомцы", "ARTICLE"));

        // Выполняем тест
        Collection<SearchResult> result = searchService.search(searchStr);

        // Проверяем результат
        Assertions.assertTrue(waitResult.size() == result.size() &&
                waitResult.containsAll(result));
    }

}