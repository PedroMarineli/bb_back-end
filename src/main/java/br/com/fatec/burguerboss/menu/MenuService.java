package br.com.fatec.burguerboss.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {
    @Autowired
    private MenuItemRepository repositoryItem;
    @Autowired
    private MenuRepository repositoryMenu;

    //search a menu with the (id) in repositoryMenu and transform then in DataListMenuItems, after that it returns it
    public DataListMenuItems listMenuItems(int id) {
        return repositoryMenu.findById(id).map(DataListMenuItems::new).orElseThrow();
    }

    //search all the menus in repositoryMenu and transform then in DataListMenu, after that it returns it
    public List<DataListMenu> listMenu() {
        return repositoryMenu.findAll().stream().map(DataListMenu::new).collect(Collectors.toList());
    }

    //create a new entity Menu and save it on the repository(database)
    public void createMenu() {
        Menu menu = new Menu();
        repositoryMenu.save(menu);
    }

    //create a new MenuItem entity and save it on the repository(database)
    public void createMenuItem(DataCreateMenuItem data) {
        MenuItem item = new MenuItem(data);
        repositoryItem.save(item);
    }
}
