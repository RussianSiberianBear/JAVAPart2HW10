package org.skypro.skyshop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.Product;

import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;
    @Mock
    private StorageService storageService;
    @InjectMocks
    private BasketService basketService;

    @Test
    public void whenAddNonExistentProductToBasket() {

        // случайный код продукта
        UUID id = UUID.randomUUID();
        // Ожидаемый результат
        String waitResult = "NoSuchProductException";

        // Настраиваем мок так, чтобы getProductById возвращал пустой Optional
        // но работает и без этого, не понял почему. Потому, что реально storage пуста?
        // Просьба пояснить
        when(storageService.getProductById(id)).thenReturn(Optional.empty());

        // Должно быть выброшено исключение при вызове basketService.addToBasket
        Exception exception = Assertions.assertThrows(NoSuchProductException.class, () -> basketService.addToBasket(id));

        // Проверяем только класс исключения, не строку сообщения об ошибке,
        // так как в самом исключении у нас создается класс, в который мы вталкиваем это сообщение,
        // а само сообщение дальше не пробрасывается
        Assertions.assertEquals(waitResult, exception.getClass().getSimpleName());

        // Дополнительно можно проверить, что продукт не был добавлен в корзину
        verify(productBasket, Mockito.never()).add(id);
    }

    @Test
    public void whenAddExistentProductToBasket() {

        // Случайный код продукта
        UUID id = UUID.randomUUID();

        // Настраиваем mocks
        Product product = mock(Product.class);
        when(storageService.getProductById(id)).thenReturn(Optional.ofNullable(product));

        // Тестируем
        basketService.addToBasket(id);

        // Проверяем
        verify(productBasket, atLeastOnce()).add(id);
    }

    @ParameterizedTest
    @NullSource
    public void whenAddNullProductIdToBasket(UUID productId) {

        // Ожидаемый результат
        String waitResult = "IllegalArgumentException";

        // Настраиваем mocks
        Product product = mock(Product.class);
        when(storageService.getProductById(productId)).thenReturn(Optional.ofNullable(product));
        Mockito.doThrow(IllegalArgumentException.class).when(productBasket).add(productId);

        // Должно быть выброшено исключение при вызове productBasket.add
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> basketService.addToBasket(productId));

        Assertions.assertEquals(waitResult, exception.getClass().getSimpleName());
    }

    @Test
    public void whenUserBusketIsEmpty() {

        when(productBasket.getProducts()).thenReturn(new TreeMap<>());
        Assertions.assertTrue(basketService.getUserBasket().getBasketItem().isEmpty());
    }

    @Test
    public void whenUserBasketIsNotEmpty() {

        // случайный код продукта
        UUID id = UUID.randomUUID();

        BasketService basketService = new BasketService(new ProductBasket(), storageService);

        // Настраиваем mocks
        Product product = mock(Product.class);
        when(storageService.getProductById(id)).thenReturn(Optional.ofNullable(product));

        basketService.addToBasket(id);

        Assertions.assertFalse(basketService.getUserBasket().getBasketItem().isEmpty());
    }
}
