package br.com.fatec.burguerboss.domain.menu;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItem> itens;

    public Menu() {
    }

    //Getters and Setters
    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public List<MenuItem> getItens() {return itens;}

    public void setItens(List<MenuItem> itens) {this.itens = itens;}

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", itens=" + itens +
                '}';
    }
}
