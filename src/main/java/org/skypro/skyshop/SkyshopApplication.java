package org.skypro.skyshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkyshopApplication {

    public static void main(String[] args) {

        try {
            SpringApplication.run(SkyshopApplication.class, args);
        } catch (Exception e) {
            System.out.println("Ошибка ! " + e.getMessage());
        }
    }

}
