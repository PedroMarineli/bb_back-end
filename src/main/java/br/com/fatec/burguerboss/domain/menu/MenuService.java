package br.com.fatec.burguerboss.domain.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {
    @Autowired
    private MenuItemRepository repositoryItem;
    @Autowired
    private MenuRepository repositoryMenu;

    private static final Logger logger = LoggerFactory.getLogger(MenuService.class);

    //search a menu with the (id) in repositoryMenu and transform then in DataListMenuItems, after that it returns it
    public Page<MenuItem> listMenuItems(int id, Pageable pagination) {
        try {
            logger.info("Listando itens do menu com ID: {} (paginado)", id);
            Menu menu = repositoryMenu.findById(id)
                    .orElseThrow(() -> new RuntimeException("Menu não encontrado com ID: " + id));

            // Retorna os itens do menu com paginação
            return repositoryItem.findByMenuId(id, pagination);
        } catch (Exception e) {
            logger.error("Erro ao listar itens do menu com ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Erro ao listar itens do menu.", e);
        }
    }

    //search all the menus in repositoryMenu and transform then in DataListMenu, after that it returns it
    public Page<DataListMenu> listMenu(Pageable pagination) {
        return repositoryMenu.findAll(pagination).map(DataListMenu::new);
    }

    //create a new entity Menu and save it on the repository(database)
    public void createMenu() {
        Menu menu = new Menu();
        repositoryMenu.save(menu);
    }

    //create a new MenuItem entity and save it on the repository(database)
    public void createMenuItem(DataCreateMenuItem data) {
        try {
            logger.info("Criando novo item de menu: {}", data.name());
            MenuItem item = new MenuItem(data);
            repositoryItem.save(item);
        } catch (Exception e) {
            logger.error("Erro ao criar item de menu: {}", e.getMessage());
            throw new RuntimeException("Erro ao criar item de menu.", e);
        }
    }

    //search a menu item with the (id) in repositoryItem and update the data with the received DTO(DataUpdateMenuItem)
    public void updateMenuItem(DataUpdateMenuItem data) {
        MenuItem item = repositoryItem.findById(data.id()).orElse(null);
        assert item != null;
        item.setAvailable(data.available());
        item.setCategory(data.category());
        item.setName(data.name());
        item.setPrice(data.price());
        repositoryItem.save(item);
    }

    //search a menu by id and delete it
    public void deleteMenu(Integer id) {
        repositoryMenu.deleteById(id);
    }

    //search a menu item by id and delete it
    public void deleteMenuItem(Integer id) {
        repositoryItem.deleteById(id);
    }
}
