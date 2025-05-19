package br.com.fatec.burguerboss.domain.completedorder;

import br.com.fatec.burguerboss.domain.order.Order;
import br.com.fatec.burguerboss.domain.payment.PaymentMethod;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "pedidos_concluidos")
public class CompletedOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Integer originalOrderId;
    
    private Integer deskNumber;
    
    private BigDecimal totalValue;
    
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    
    private LocalDateTime completionDate;
    
    private String description;
    
    @OneToMany(mappedBy = "completedOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompletedOrderItem> items = new ArrayList<>();
    
    public CompletedOrder() {
    }
    
    public CompletedOrder(Order order) {
        this.originalOrderId = order.getId();
        this.deskNumber = order.getDesk().getId(); // Corrigido para usar getNumber() em vez de getId()
        this.totalValue = order.getTotalValue();
        this.paymentMethod = order.getPaymentMethod();
        this.completionDate = LocalDateTime.now();
        this.description = order.getDescription();
        
        // Copiar itens do pedido original
        if (order.getOrderItems() != null) {
            this.items = order.getOrderItems().stream()
                .map(CompletedOrderItem::new)
                .collect(Collectors.toList());
                
            // Estabelecer a relação bidirecional
            this.items.forEach(item -> item.setCompletedOrder(this));
        }
    }
    
    // Getters e Setters existentess
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOriginalOrderId() {
        return originalOrderId;
    }

    public void setOriginalOrderId(Integer originalOrderId) {
        this.originalOrderId = originalOrderId;
    }

    public Integer getDeskNumber() {
        return deskNumber;
    }

    public void setDeskNumber(Integer deskNumber) {
        this.deskNumber = deskNumber;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    // Novos getters e setters para itens
    public List<CompletedOrderItem> getItems() {
        return items;
    }

    public void setItems(List<CompletedOrderItem> items) {
        this.items = items;
    }
    
    public void addItem(CompletedOrderItem item) {
        items.add(item);
        item.setCompletedOrder(this);
    }
    
    public void removeItem(CompletedOrderItem item) {
        items.remove(item);
        item.setCompletedOrder(null);
    }
}
