package br.com.fatec.burguerboss.desk;

import jakarta.persistence.*;

@Entity
@Table(name = "tables")
public class Desk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "filled")
    private boolean filled;

    public Desk(boolean filled) {
        this.filled = filled;} //construtor iniciado com "filled"

    public Desk() {} //construtor vazio

    public Desk(int id, boolean filled) {
        this.id = id;
        this.filled = filled;
    } //construtor completoo

    public void changeStatus(){this.filled = !this.filled;} //inverte o "filled" da mesa

    @Override
    public String toString() {
        return "Desk{" +
                "Id=" + id +
                ", Filled=" + filled +
                '}';
    } //toString da classe "desk"

    public int getId() {return id;} //getId

    public void setId(int id) {
        this.id = id;} //setId

    public boolean isFilled() {return filled;} //getFilled

    public void setFilled(boolean filled) {
        this.filled = filled;} //setFilled
}
