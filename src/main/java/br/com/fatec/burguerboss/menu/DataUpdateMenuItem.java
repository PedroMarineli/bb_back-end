package br.com.fatec.burguerboss.menu;

import java.math.BigDecimal;

public record DataUpdateMenuItem(
        int id,
        BigDecimal price,
        String name,
        String category,
        boolean available,
        Menu menu
) {
}
