package br.com.fatec.burguerboss.domain.menu;

import java.util.List;
import java.util.stream.Collectors;

public record DataListMenuItems(
        Integer id,
        List<DataListMenuItem> items
) {
    public DataListMenuItems(Menu menu) {
        this(
                menu.getId(),
                menu.getItens().stream().map(DataListMenuItem::new).collect(Collectors.toList())
        );
    }
}
