package br.com.fatec.burguerboss.principal;

import br.com.fatec.burguerboss.models.Desk;
import br.com.fatec.burguerboss.repository.DeskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
                    showTablesList();
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
        Desk desk = searchTableById(selectTable());
        desk.changeStatus();
        repository.save(desk);
        System.out.println("Status alterado!");
    } //altera o status da mesa no banco de dados

    private int selectTable() {
        System.out.print("Escolha uma mesa (id): ");

        return read.nextInt();
    } //retorna o id da mesa selecionada

    public void changeNumberOfTables() {
        System.out.println("Escreva o numero de mesas que irao compor o restaurante: ");
        int input = read.nextInt();
        verifyNumberOfTables(input);
        System.out.println("Quantidade alterada!");
    } //altera o numero de mesas na lista

    private void verifyNumberOfTables(int numberOfTables) {
        if (repository.count() < numberOfTables){
            while (repository.count()!= numberOfTables){
                createTable();
            }
        } else if (repository.count() > numberOfTables) {
            while (repository.count()!= numberOfTables){
                deleteTable();
            }
        }
    } //verifica o numero de mesas existentes no banco de dados

    private void deleteTable() {
        Desk deletedDesk = repository.findFirstByFilledIsFalse();
        repository.delete(deletedDesk);
    } //Remove apenas um objeto onde o atributo e falso

    private void showTablesList() {
        desks = repository.findAll();
        System.out.println("tamanho da lista: " + repository.count());
        System.out.println("lista:");
        for (Desk desk : desks) {
            System.out.println(desk.toString());
        }
    } //visualiza a lista de mesas

    public void createTable(){
        Desk mesa = new Desk(false);
        repository.save(mesa);
    } //instancia uma nova mesa e adiciona ela na lista e no banco de dados

    public Desk searchTableById(int id){
        Optional<Desk> desk = repository.findById(id);
        return desk.orElse(null);
    } //retorna uma mesa pelo Id
}
