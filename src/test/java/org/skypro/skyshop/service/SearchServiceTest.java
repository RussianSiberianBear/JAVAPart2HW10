package org.skypro.skyshop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;

import java.util.*;

public class SearchServiceTest {

    private StorageService storageService;
    private SearchService searchService;

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = "абракадабра")
    public void whenIsEmptyStorageService(String searchStr) {

        storageService = new StorageService(new HashMap<>(), new HashMap<>());
        searchService = new SearchService(storageService);
        // Выполняем тест
        Collection<SearchResult> result = searchService.search(searchStr);
        // Проверяем результат
        Assertions.assertEquals(Collections.emptyList(), result);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = "абракадабра")
    public void whenStorageIsNotEmptyButIsNoSuitableProduct(String searchStr) {

        // Делаем тестовые данные, считая, что они очень маленькие и подойдут для теста,
        // хотя они сейчас равны реальным данным, но считаем, что реальные данные могут быть очень большого размера,
        // а тесты можем провести на данных маленького объема
        addTestData();
        searchService = new SearchService(storageService);
        // Выполняем тест
        Collection<SearchResult> result = searchService.search(searchStr);

        // Проверяем результат
        Assertions.assertEquals(Collections.emptyList(), result);
    }

    @ParameterizedTest
    @ValueSource(strings = "кош")
    public void whenStorageIsNotEmptyAndIsExistsSuitableProduct(String searchStr) {

        // Делаем тестовые данные, считая, что они очень маленькие и подойдут для теста,
        // хотя они сейчас равны реальным данным, но считаем, что реальные данные могут быть очень большого размера,
        // а тесты можем провести на данных маленького объема
        addTestData();
        searchService = new SearchService(storageService);

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

    private void addTestData() {

        Map<UUID, Product> productStorage = new HashMap<>();
        Map<UUID, Article> articleStorage = new HashMap<>();

        Product[] productsArr = new Product[]{
                new DiscountedProduct("1-й продукт", 100, 10),
                new SimpleProduct("2-й продукт", 200),
                new DiscountedProduct("3-й продукт", 300, 20),
                new FixPriceProduct("4-й продукт"),
                new FixPriceProduct("4-й продукт"),
                new SimpleProduct("5-й продукт", 500)
        };
        Arrays.stream(productsArr).forEach(product -> productStorage.put(product.getId(), product));

        Article[] articleArr = new Article[]{
                new Article("Новости", "Новости дня."),
                new Article("Корм для животных", "В данной статье рассказывается о видах кормов для домашних животных-кошек и собак."),
                new Article("Домашние питомцы", "Статья о кошках и собаках. Кошки и собаки давние домашние питомцы."),
                new Article("Вакансии", "Свежие вакансии на сегодня."),
                new Article("Женские товары", "Косметика, духи и другие штучки для женщин.")
        };
        Arrays.stream(articleArr).forEach(article -> articleStorage.put(article.getId(), article));

        storageService = new StorageService(productStorage, articleStorage);
    }

}