package br.com.fatec.burguerboss.desk;

import jakarta.validation.constraints.NotNull;

public record DataCreateDesk(
        @NotNull
        int deskNumber
) {
}
