package br.com.fatec.burguerboss.order;

import br.com.fatec.burguerboss.desk.Desk;
import br.com.fatec.burguerboss.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record DataUpdateOrder(
        Integer id,
        BigDecimal totalValue,
        PaymentMethod paymentMethod,
        OrderStatus orderStatus,
        Desk desk
) {
}
