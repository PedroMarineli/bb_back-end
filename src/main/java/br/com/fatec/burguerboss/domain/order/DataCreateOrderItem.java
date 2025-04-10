package br.com.fatec.burguerboss.domain.order;

import br.com.fatec.burguerboss.domain.menu.MenuItem;

public record DataCreateOrderItem(
        Integer quantity,
        MenuItem menuItem,
        Order order
) {
    @Override
    public Order order() {
        return order;
    }

    @Override
    public MenuItem menuItem() {
        return menuItem;
    }

    @Override
    public Integer quantity() {
        return quantity;
    }
}
