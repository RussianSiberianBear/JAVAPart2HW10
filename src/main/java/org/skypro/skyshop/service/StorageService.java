package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.*;
import org.springframework.stereotype.Service;
import org.skypro.skyshop.model.search.Searchable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {

    private final Map<UUID, Product> productStorage;
    private final Map<UUID, Article> articleStorage;

    public StorageService() {
        productStorage = new HashMap<>();
        articleStorage = new HashMap<>();
        this.addToStorage();
    }

    public Collection<Product> getProductStorage() {
        return new ArrayList<>(this.productStorage.values());
    }

    public Collection<Article> getArticleStorage() {
        return new ArrayList<>(this.articleStorage.values());
    }

    public Collection<Searchable> getSearchableStorage() {
        return Stream.concat(
                productStorage.values().stream(),
                articleStorage.values().stream()
        ).collect(Collectors.toList());
    }

    private void addToStorage() {
        Product[] productsArr = new Product[]{
                new DiscountedProduct("1-й продукт", 100, 10),
                new SimpleProduct("2-й продукт", 200),
                new DiscountedProduct("3-й продукт", 300, 20),
                new FixPriceProduct("4-й продукт"),
                new FixPriceProduct("4-й продукт"),
                new SimpleProduct("5-й продукт", 500)
        };
        Arrays.stream(productsArr).forEach(product -> this.productStorage.put(product.getId(), product));

        Article[] articleArr = new Article[]{
                new Article("Новости", "Новости дня."),
                new Article("Корм для животных", "В данной статье рассказывается о видах кормов для домашних животных-кошек и собак."),
                new Article("Домашние питомцы", "Статья о кошках и собаках. Кошки и собаки давние домашние питомцы."),
                new Article("Вакансии", "Свежие вакансии на сегодня."),
                new Article("Женские товары", "Косметика, духи и другие штучки для женщин.")
        };
        Arrays.stream(articleArr).forEach(article -> this.articleStorage.put(article.getId(), article));
    }

}
