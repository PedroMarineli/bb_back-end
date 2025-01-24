package br.com.fatec.burguerboss.desk;

import jakarta.validation.constraints.NotNull;

public record DataCreateDesk(
        @NotNull(message = "this field cannot be null")
        Integer deskNumber
) {
}
