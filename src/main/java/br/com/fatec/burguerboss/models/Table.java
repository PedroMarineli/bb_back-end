package br.com.fatec.burguerboss.models;

public class Table{
    private int Id;
    private boolean Filled;

    public Table(boolean filled) {Filled = filled;} //construtor iniciado com "filled"

    public Table() {} //construtor vazio

    public Table(int id, boolean filled) {
        Id = id;
        Filled = filled;
    }

    public void changeStatus(){this.Filled = !this.Filled;} //inverte o "filled" da mesa

    @Override
    public String toString() {
        return "Table{" +
                "Id=" + Id +
                ", Filled=" + Filled +
                '}';
    } //toString da classe "table"

    public int getId() {return Id;} //getId

    public void setId(int id) {Id = id;} //setId

    public boolean isFilled() {return Filled;} //getFilled

    public void setFilled(boolean filled) {Filled = filled;} //setFilled
}
