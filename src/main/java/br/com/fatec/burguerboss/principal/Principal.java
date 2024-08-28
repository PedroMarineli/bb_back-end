package br.com.fatec.burguerboss.principal;

import br.com.fatec.burguerboss.models.Desk;
import br.com.fatec.burguerboss.repository.DeskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private final DeskRepository repository;
    List<Desk> desks = new ArrayList<>(); //instancia uma lista de mesas
    Scanner read = new Scanner(System.in); //instancia um Scanner

    public Principal(DeskRepository repository) {
        this.repository = repository;
    }

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
                        2 - Alterar status da mesa
                        3 - Visualizar todas as mesas            \s
                        
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
                    changeTableStatus();
                    break;
                case 3:
                    showTablesList(desks);
                    break;
                case 99:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    } //cria e mostra um menu (cmd) para funções relacionadas ao gerenciamento de mesas

    private void changeTableStatus() {
        searchTableById(selectTable()).changeStatus();
        System.out.println("Status alterado!");
    } //altera o status da mesa

    private int selectTable() {
        System.out.print("Escolha uma mesa (id): ");

        return read.nextInt();
    } // retorna o id da mesa selecionada

    public void changeNumberOfTables() {
        System.out.println("Escreva o numero de mesas que irao compor o restaurante: ");
        int input = read.nextInt();
        verifyNumberOfTables(input);
        System.out.println("Quantidade alterada!");
    } //altera o numero de mesas na lista

    private void verifyNumberOfTables(int numberOfTables) {
        if (repository.count() < numberOfTables){
            while (repository.count()!= numberOfTables){
                createTable(desks);
            }
        } else if (repository.count() > numberOfTables) {
            while (repository.count()!= numberOfTables){
                deleteTable(desks);
            }
        }
    } //verifica o numero de mesas existentes no banco de dados

    private void deleteTable(List<Desk> desks) {
        for (int i = 0; i < repository.count(); i++) {
            if (!desks.get(i).isFilled()){
                desks.remove(i);
                break;
            }
        }
    } // Remove apenas um objeto onde o atributo e falso

    private void showTablesList(List<Desk> desks) {
        System.out.println("tamanho da lista: " + repository.count());
        System.out.println("lista:");
        for (Desk desk : desks) {
            System.out.println(desk.toString());
        }
    } //visualiza a lista de mesas

    public void createTable(List<Desk> desks){
        Desk mesa = new Desk(false);
        desks.add(mesa);
        repository.save(mesa);
    } //instancia uma nova mesa e adiciona ela na lista e no banco de dados

    public Desk searchTableById(int id){
        return desks.stream()
                        .filter(desk -> desk.getId()==id)
                        .findFirst()
                        .orElse(null);
    } //retorna uma mesa pelo Id
}
