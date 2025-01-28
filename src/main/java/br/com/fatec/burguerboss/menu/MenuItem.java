package br.com.fatec.burguerboss.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "MenuItens")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal price;
    private String name;
    private String category;
    private boolean available;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public MenuItem() {
    }

    public MenuItem(DataCreateMenuItem data) {
        this.price = data.price();
        this.name = data.name();
        this.category = data.category();
        this.available = data.available();
        this.menu = data.menu();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
