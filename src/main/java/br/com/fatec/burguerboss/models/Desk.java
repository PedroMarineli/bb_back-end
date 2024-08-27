package br.com.fatec.burguerboss.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tables")
public class Desk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(unique = false)
    private boolean Filled;

    public Desk(boolean filled) {Filled = filled;} //construtor iniciado com "filled"

    public Desk() {} //construtor vazio

    public Desk(int id, boolean filled) {
        Id = id;
        Filled = filled;
    }

    public void changeStatus(){this.Filled = !this.Filled;} //inverte o "filled" da mesa

    @Override
    public String toString() {
        return "Desk{" +
                "Id=" + Id +
                ", Filled=" + Filled +
                '}';
    } //toString da classe "desk"

    public int getId() {return Id;} //getId

    public void setId(int id) {Id = id;} //setId

    public boolean isFilled() {return Filled;} //getFilled

    public void setFilled(boolean filled) {Filled = filled;} //setFilled
}
