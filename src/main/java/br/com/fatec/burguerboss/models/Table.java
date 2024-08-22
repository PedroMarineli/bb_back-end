package br.com.fatec.burguerboss.models;

public class Table {
    private double Id;
    private boolean Filled;

    public void changeStatus(){
        this.Filled = !this.Filled;
    }
}
