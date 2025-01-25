package br.com.fatec.burguerboss.menu;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    //search a menu item with the (id) in repositoryItem and update the data with the received DTO(DataUpdateMenuItem)
    public void updateMenuItem(DataUpdateMenuItem data) {
        MenuItem item = repositoryItem.findById(data.id()).orElse(null);
        assert item != null;
        item.setAvailable(data.available());
        item.setCategory(data.category());
        item.setName(data.name());
        item.setPrice(data.price());
        item.setMenu(data.menu());
        repositoryItem.save(item);
    }

    //search a menu by id e delete it
    public void deleteMenu(Integer id) {
        repositoryMenu.deleteById(id);
    }
}
