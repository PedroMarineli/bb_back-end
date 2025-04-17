package br.com.fatec.burguerboss.domain.desk;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DataUpdateDesk(
        @NotNull(message = "O ID da mesa não pode ser nulo.")
        @Min(value = 1, message = "O ID da mesa deve ser maior ou igual a 1.")
        Integer id
) {

}
