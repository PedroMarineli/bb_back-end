package br.com.fatec.burguerboss.desk;

import jakarta.persistence.*;

@Entity
@Table(name = "tables")
public class Desk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "filled")
    private boolean filled;

    public Desk(boolean filled) {
        this.filled = filled;} //construtor iniciado com "filled"

    public Desk() {} //construtor vazio

    public Desk(Integer id, boolean filled) {
        this.id = id;
        this.filled = filled;
    } //construtor completo

    public Desk(DataListDesk data){
        this.id = data.id();
        this.filled = data.filled();
    }

    public void changeStatus(){this.filled = !this.filled;} //inverte o "filled" da mesa

    @Override
    public String toString() {
        return "Desk{" +
                "Id=" + id +
                ", Filled=" + filled +
                '}';
    } //toString da classe "desk"

    public Integer getId() {return id;} //getId

    public void setId(Integer id) {
        this.id = id;} //setId

    public boolean isFilled() {return filled;} //getFilled

    public void setFilled(boolean filled) {
        this.filled = filled;} //setFilled
}
