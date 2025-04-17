package br.com.fatec.burguerboss.domain.order;

import br.com.fatec.burguerboss.domain.desk.Desk;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record DataCreateOrder(
        List<OrderItem> orderItems,

        @NotNull(message = "A mesa não pode ser nula.")
        Desk desk,

        @Size(max = 255, message = "A descrição do pedido deve ter no máximo 255 caracteres.")
        String description
) {}
