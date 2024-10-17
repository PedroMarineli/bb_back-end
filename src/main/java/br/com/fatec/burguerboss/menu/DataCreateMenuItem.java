package br.com.fatec.burguerboss.menu;

import java.math.BigDecimal;

public record DataCreateMenuItem(
        BigDecimal price,
        String name,
        String category,
        boolean available,
        Menu menu
) {

}
