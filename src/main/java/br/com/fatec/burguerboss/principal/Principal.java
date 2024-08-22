package br.com.fatec.burguerboss.principal;

public class Principal {
    public void showMenu() {
        var option = -1;

        while(option != 99){
            var menu = """
                        *|Burguer Boss|*
                        
                        1- Manter mesas
                    """;

            System.out.println(menu);
        }


    }
}
