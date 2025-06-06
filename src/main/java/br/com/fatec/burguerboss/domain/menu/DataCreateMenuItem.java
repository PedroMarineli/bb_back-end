package br.com.fatec.burguerboss.domain.menu;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record DataCreateMenuItem(
        @NotNull(message = "O preço do item não pode ser nulo.")
        @Min(value = 0, message = "O preço do item deve ser maior ou igual a 0.")
        BigDecimal price,

        @NotNull(message = "O nome do item não pode ser nulo.")
        @Size(min = 1, max = 100, message = "O nome do item deve ter entre 1 e 100 caracteres.")
        String name,

        @NotNull(message = "A categoria do item não pode ser nula.")
        @Size(min = 1, max = 50, message = "A categoria do item deve ter entre 1 e 50 caracteres.")
        String category,

        boolean available,

        @Size(max = 255, message = "A descrição do item deve ter no máximo 255 caracteres.")
        String description,

        @NotNull(message = "O menu associado não pode ser nulo.")
        Menu menu
) {
}


