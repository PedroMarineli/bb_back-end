package br.com.fatec.burguerboss.domain.menu;

public record DataListMenu(
        Integer id,
        Integer menuLength
) {
    public DataListMenu(Menu menu) {
        this(
                menu.getId(),
                menu.getItens().size()
        );
    }
}
