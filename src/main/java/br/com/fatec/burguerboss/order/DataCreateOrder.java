package br.com.fatec.burguerboss.order;

import br.com.fatec.burguerboss.desk.Desk;
import br.com.fatec.burguerboss.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record DataCreateOrder(
         BigDecimal totalValue,
         PaymentMethod paymentMethod,
         List<OrderItem> orderItems,
         OrderStatus orderStatus,
         Desk desk
) {
}
