package br.com.fatec.burguerboss.domain.desk;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DataCreateDesk(
        @NotNull(message = "O número da mesa não pode ser nulo.")
        @Min(value = 0, message = "O número da mesa deve ser maior ou igual a 0.")
        @Max(value = 99, message = "O número da mesa deve ser menor ou igual a 99.")
        Integer deskNumber
) {
}
