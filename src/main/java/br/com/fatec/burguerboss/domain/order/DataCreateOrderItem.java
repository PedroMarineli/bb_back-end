package br.com.fatec.burguerboss.domain.order;

import br.com.fatec.burguerboss.domain.menu.MenuItem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DataCreateOrderItem(
        @NotNull(message = "A quantidade não pode ser nula.")
        @Min(value = 1, message = "A quantidade deve ser pelo menos 1.")
        Integer quantity,

        @NotNull(message = "O item do menu não pode ser nulo.")
        MenuItem menuItem,

        @NotNull(message = "O pedido não pode ser nulo.")
        Order order
) {
    // Removendo os métodos sobrescritos desnecessários, pois os métodos gerados pelo record já são suficientes.
}
