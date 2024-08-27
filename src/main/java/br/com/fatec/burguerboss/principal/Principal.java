package br.com.fatec.burguerboss.principal;

import br.com.fatec.burguerboss.models.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    List<Table> tables = new ArrayList<>(); //instancia uma lista de mesas
    Scanner read = new Scanner(System.in); //instancia um Scanner

    public void showMenu() {
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
    } //cria e mostra um menu (cmd) para selecionar as principais funções do código

    public void showTableMenu() {
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
                    break;
                case 3, 4:
                    break;
                case 5:
                    showTablesList(tables);
                    break;
                case 99:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    } //cria e mostra um menu (cmd) para funções relacionadas ao gerenciamento de mesas

    private int selectTable() {
        System.out.print("Escolha uma mesa (id): ");
        var input = read.nextInt();

        return input;
    } // retorna o id da mesa selecionada

    public void changeNumberOfTables() {
        System.out.println("Escreva o numero de mesas que irao compor o restaurante: ");
        int input = read.nextInt();
        verifyNumberOfTables(input);
        System.out.println("Quantidade alterada!");
    } //altera o numero de mesas na lista

    private void verifyNumberOfTables(int numberOfTables) {
        if (tables.size() < numberOfTables){
            while (tables.size()!= numberOfTables){
                createTable(tables);
            }
        } else if (tables.size() > numberOfTables) {
            while (tables.size()!= numberOfTables){
                deleteTable(tables);
            }
        }
    } //verifica o numero de mesas existentes na lista

    private void deleteTable(List<Table> tables) {
        for (int i = 0; i < tables.size(); i++) {
            if (!tables.get(i).isFilled()){
                tables.remove(i);
                break;
            }
        }
    } // Remove apenas um objeto onde o atributo e falso

    private void showTablesList(List<Table> tables) {
        System.out.println("tamanho da lista: " + tables.size());
        System.out.println("lista:");
        for (Table table : tables) {
            System.out.println(table.toString());
        }
    } //visualiza a lista de mesas

    public void createTable(List<Table> tables){
        Table mesa = new Table((int)(Math.random()*100), false);
        tables.add(mesa);
    } //instancia uma nova mesa e adiciona ela na lista
}
