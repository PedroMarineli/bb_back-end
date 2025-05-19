package br.com.fatec.burguerboss.domain.completedorder;

import br.com.fatec.burguerboss.domain.order.OrderItem;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "completed_order_items")
public class CompletedOrderItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "completed_order_id")
    private CompletedOrder completedOrder;
    
    private Integer menuItemId;
    
    private String menuItemName;
    
    private Integer quantity;
    
    private BigDecimal unitPrice;
    
    public CompletedOrderItem() {
    }
    
    public CompletedOrderItem(OrderItem orderItem) {
        this.menuItemId = orderItem.getMenuItem().getId();
        this.menuItemName = orderItem.getMenuItem().getName();
        this.quantity = orderItem.getQuantity();
        this.unitPrice = orderItem.getMenuItem().getPrice();
    }
    
    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CompletedOrder getCompletedOrder() {
        return completedOrder;
    }

    public void setCompletedOrder(CompletedOrder completedOrder) {
        this.completedOrder = completedOrder;
    }

    public Integer getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Integer menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
