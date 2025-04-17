package br.com.fatec.burguerboss.domain.order;

import br.com.fatec.burguerboss.domain.desk.Desk;
import br.com.fatec.burguerboss.domain.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DataUpdateOrder(
        @NotNull(message = "O ID não pode ser nulo.")
        Integer id,

        @NotNull(message = "O valor total não pode ser nulo.")
        @Min(value = 0, message = "O valor total deve ser maior ou igual a zero.")
        BigDecimal totalValue,

        @NotNull(message = "O método de pagamento não pode ser nulo.")
        PaymentMethod paymentMethod,

        List<OrderItem> orderItems,

        @NotNull(message = "O status do pedido não pode ser nulo.")
        OrderStatus orderStatus,

        @NotNull(message = "A mesa não pode ser nula.")
        Desk desk,

        @Size(max = 255, message = "A descrição do pedido deve ter no máximo 255 caracteres.")
        String description
) {}
