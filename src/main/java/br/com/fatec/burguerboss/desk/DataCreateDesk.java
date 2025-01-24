package br.com.fatec.burguerboss.desk;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DataCreateDesk(
        @NotNull
        @Min(0)
        @Max(99)
        Integer deskNumber
) {
}
