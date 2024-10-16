package br.com.fatec.burguerboss.menu;

import java.math.BigDecimal;

public record DataListMenuItem(
         int id,
         BigDecimal price,
         String name,
         String category,
         boolean available,
         Menu menu
) {
    public DataListMenuItem(MenuItem menuItem){
        this(
                menuItem.getId(),
                menuItem.getPrice(),
                menuItem.getName(),
                menuItem.getCategory(),
                menuItem.isAvailable(),
                menuItem.getMenu()
        );
    }
}
