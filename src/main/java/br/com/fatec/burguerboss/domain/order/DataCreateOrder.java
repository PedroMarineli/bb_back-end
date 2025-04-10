package br.com.fatec.burguerboss.domain.order;

import br.com.fatec.burguerboss.domain.desk.Desk;
import br.com.fatec.burguerboss.domain.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record DataCreateOrder(
         List<OrderItem> orderItems,
         Desk desk
) {
}
