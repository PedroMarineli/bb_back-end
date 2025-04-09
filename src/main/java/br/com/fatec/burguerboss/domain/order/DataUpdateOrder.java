package br.com.fatec.burguerboss.domain.order;

import br.com.fatec.burguerboss.domain.desk.Desk;
import br.com.fatec.burguerboss.domain.payment.PaymentMethod;

import java.math.BigDecimal;

public record DataUpdateOrder(
        Integer id,
        PaymentMethod paymentMethod,
        OrderStatus orderStatus,
        Desk desk
) {
}
