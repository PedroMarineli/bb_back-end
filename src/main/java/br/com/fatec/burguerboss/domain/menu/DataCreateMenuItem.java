package br.com.fatec.burguerboss.domain.menu;

import java.math.BigDecimal;

public record DataCreateMenuItem(
        BigDecimal price,
        String name,
        String category,
        boolean available,
        Menu menu
) {

}
