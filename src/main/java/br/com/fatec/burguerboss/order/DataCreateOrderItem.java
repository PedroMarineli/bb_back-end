package br.com.fatec.burguerboss.order;

import br.com.fatec.burguerboss.menu.MenuItem;

public record DataCreateOrderItem(
        Integer quantity,
        MenuItem menuItem,
        Order order
) {
}
