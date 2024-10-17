package br.com.fatec.burguerboss.menu;

import br.com.fatec.burguerboss.desk.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {
    @Autowired
    private MenuItemRepository repositoryItem;
    @Autowired
    private MenuRepository repositoryMenu;

    public List<DataListMenu> listMenuItem() {
        return repositoryMenu.findAll().stream().map(menu -> {
            List<DataListMenuItem> itens = menu.getItens().stream()
                    .map(DataListMenuItem::new)
                    .collect(Collectors.toList());
            return new DataListMenu(itens);
        }).collect(Collectors.toList());
    }

    public void registerMenuItem(@RequestBody DataCreateMenuItem data) {
        MenuItem item = new MenuItem(data);
        repositoryItem.save(item);
    }

    public void registerMenu() {
        Menu menu = new Menu();
        repositoryMenu.save(menu);
    }

    public void updateMenuItem(@RequestBody DataUpdateMenuItem data) {
        MenuItem item = searchMenuItemById(data.id());

        item.setPrice(data.price());
        item.setName(data.name());
        item.setCategory(data.category());
        item.setAvailable(data.available());
        item.setMenu(data.menu());

        repositoryItem.save(item);

    }

    public MenuItem searchMenuItemById(int id){
        Optional<MenuItem> item = repositoryItem.findById(id);
        return item.orElse(null);
    }
}
