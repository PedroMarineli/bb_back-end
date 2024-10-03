package br.com.fatec.burguerboss.desk;

import jakarta.validation.constraints.NotNull;

public record DataUpdateDesk(
        @NotNull
        int id
) {

}
