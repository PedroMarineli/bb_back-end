package br.com.fatec.burguerboss.domain.order;

import br.com.fatec.burguerboss.domain.menu.MenuItem;

public record DataUpdateOrderItem(
        Integer id,
        Integer quantity,
        MenuItem menuItem,
        Order order
) {
}
