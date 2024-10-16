package br.com.fatec.burguerboss.menu;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    private int id;
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItem> itens;
}
