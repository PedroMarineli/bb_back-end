package br.com.fatec.burguerboss.models;

import br.com.fatec.burguerboss.principal.Principal;
import br.com.fatec.burguerboss.repository.TableRepository;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Entity
public class Table{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column
    private boolean Filled;

    public Table(boolean filled) {
        Filled = filled;
    }

    public Table() {

    }

    public static void addTable() {

    }



    public static void selectTable() {
        Scanner read = new Scanner(System.in);

        System.out.println("Selecione a mesa: ");

        read.nextDouble();
    }

    public void changeStatus(){
        this.Filled = !this.Filled;
    }

    public double getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public boolean isFilled() {
        return Filled;
    }

    public void setFilled(boolean filled) {
        Filled = filled;
    }

    @Override
    public String toString() {
        return "Table{" +
                "Id=" + Id +
                ", Filled=" + Filled +
                '}';
    }
}
