package br.com.fatec.burguerboss.menu;

public record DataListMenu(
        Integer id,
        Integer MenuLength
) {
    //a constructor that receives a menu and return a DataListMenu
    public DataListMenu(Menu menu) {
        this(
                menu.getId(),
                menu.getItens().size()
        );
    }
}
