package br.com.fatec.burguerboss.order;

import br.com.fatec.burguerboss.desk.Desk;
import br.com.fatec.burguerboss.payment.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;

public record DataListOrder(
        Integer id,
        BigDecimal totalValue,
        PaymentMethod paymentMethod,
        List<OrderItem> orderItems,
        OrderStatus orderStatus,
        Desk desk
) {
    public DataListOrder(Order order){
        this(
                order.getId(),
                order.getTotalValue(),
                order.getPaymentMethod(),
                order.getOrderItems(),
                order.getOrderStatus(),
                order.getDesk()
        );
    }
}
