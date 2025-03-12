package br.com.fatec.burguerboss.domain.menu;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DataUpdateMenuItem(
        @NotNull
        int id,
        BigDecimal price,
        String name,
        String category,
        boolean available,
        Menu menu
) {
}
