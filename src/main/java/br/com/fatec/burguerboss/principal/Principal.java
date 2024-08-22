package br.com.fatec.burguerboss.principal;

import java.util.Scanner;

public class Principal {
    Scanner read = new Scanner(System.in);
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

        }
    }

    public void showTableMenu() {
        var option = -1;

        while(option != 99){
            var menu = """
                        *|Burguer Boss|*
                        
                        1 - Adicionar mesa
                        2 - Excluir mesa
                        3 - Alterar status da mesa
                                       
                        
                        99 - Sair
                    """;

            System.out.println(menu);

            System.out.print("Opcao escolhida: ");
            option = read.nextInt();

        }
    }
}
