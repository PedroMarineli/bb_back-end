package br.com.fatec.burguerboss.principal;

import br.com.fatec.burguerboss.models.Table;
import br.com.fatec.burguerboss.repository.TableRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private final TableRepository repositorio;

    public Principal(TableRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void showMenu() {
        Scanner read = new Scanner(System.in);
        var option = -1;

        while(option != 99){
            var menu = """
                        *|Burguer Boss|*
                        
                        1 - Gerenciar mesas
                                       
                        
                        99 - Sair
                    """;

            System.out.println(menu);

            System.out.print("Opcao escolhida: ");
            option = read.nextInt();

            switch (option){
                case 1:
                    showTableMenu();
                    break;
                case 99:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }

        }
    }

    public void showTableMenu() {
        Scanner read = new Scanner(System.in);
        var option = -1;

        while(option != 99){
            var menu = """
                        *|Gerenciar mesas|*
                        
                        1 - Alterar quantidade de mesas
                        3 - Alterar status da mesa
                        4 - Consultar status da mesa
                        5 - Visualizar todas as mesas
                                       
                        
                        99 - Sair
                    """;

            System.out.println(menu);

            System.out.print("Opcao escolhida: ");
            option = read.nextInt();

            switch (option){
                case 1:
                    changeNumberOfTables();
                    break;
                case 2:
                    Table.selectTable();
                    break;
                case 99:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    public void changeNumberOfTables() {
        List<Table> tables = new ArrayList<>();
        Scanner read = new Scanner(System.in);
        System.out.println("Escreva o numero de mesas que irao compor o restaurante: ");
        int input = read.nextInt();
        if (tables.size()<input){
            while (tables.size()!=input){
                createTable(tables);
            }
        }
        for (Table table : tables) {
            System.out.println(table.toString());
        }
    }
    public void createTable(List<Table> tables){
        Table mesa = new Table(false);
        tables.add(mesa);
        repositorio.save(mesa);
    }
}
