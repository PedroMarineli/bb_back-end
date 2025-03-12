package br.com.fatec.burguerboss.domain.menu;

import java.util.List;
import java.util.stream.Collectors;

public record DataListMenuItems(
        Integer id,
        List<DataListMenuItem> items
) {
    //constructor that receive a Menu and create a DataListMenuItem
    public DataListMenuItems(Menu menu) {
        this(
                menu.getId(),
                menu.getItens().stream().map(DataListMenuItem::new).collect(Collectors.toList())
        );
    }
}
