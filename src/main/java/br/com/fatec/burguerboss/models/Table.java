package br.com.fatec.burguerboss.models;

import java.util.List;
import java.util.Scanner;

public class Table {
    private double Id;
    private boolean Filled;

    public Table(double id, boolean filled) {
        Id = id;
        Filled = filled;
    }

    public static void addTable() {

    }

    public static void changeNumberOfTables(List<Table> tables) {
        Scanner read = new Scanner(System.in);
        System.out.println("Escreva o numero de mesas que irao compor o restaurante: ");
        int input = read.nextInt();
        if (tables.size()<input){

        }


    }

    public static double selectTable() {
        Scanner read = new Scanner(System.in);

        System.out.println("Selecione a mesa: ");

        return read.nextDouble();
    }

    public void changeStatus(){
        this.Filled = !this.Filled;
    }

    public double getId() {
        return Id;
    }

    public void setId(double id) {
        Id = id;
    }

    public boolean isFilled() {
        return Filled;
    }

    public void setFilled(boolean filled) {
        Filled = filled;
    }
}
