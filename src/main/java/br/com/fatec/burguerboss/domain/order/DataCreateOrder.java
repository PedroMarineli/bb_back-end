package br.com.fatec.burguerboss.domain.order;

import br.com.fatec.burguerboss.domain.desk.Desk;

import java.util.List;

public record DataCreateOrder(
        List<OrderItem> orderItems,
        Desk desk,
        String description
) {}
