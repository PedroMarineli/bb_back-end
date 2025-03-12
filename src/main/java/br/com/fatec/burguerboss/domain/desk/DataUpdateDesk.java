package br.com.fatec.burguerboss.domain.desk;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DataUpdateDesk(
        @NotNull
        @Min(0)
        Integer id
) {

}
