package org.skypro.skyshop;

import org.skypro.skyshop.controller.ShopController;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class ConfigurationApp {

    @Bean
    public StorageService storageService() {
        return new StorageService();
    }

    @Bean
    @SessionScope
    public ProductBasket productBasket() {
        return new ProductBasket();
    }

    @Bean
    public BasketService basketService(ProductBasket productBasket, StorageService storageService) {
        return new BasketService(productBasket, storageService);
    }

    @Bean
    public SearchService searchService(StorageService storageService) {
        return new SearchService(storageService);
    }

    @Bean
    public ShopController shopController(StorageService storageService, SearchService searchService, BasketService basketService) {
        return new ShopController(storageService, searchService, basketService);
    }

}
