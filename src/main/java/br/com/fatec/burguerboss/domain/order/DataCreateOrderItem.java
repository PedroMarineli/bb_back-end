package br.com.fatec.burguerboss.domain.order;

import br.com.fatec.burguerboss.domain.menu.MenuItem;

public record DataCreateOrderItem(
        Integer quantity,
        MenuItem menuItem,
        Order order
) {
}
