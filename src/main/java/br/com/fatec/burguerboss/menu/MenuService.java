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

    //this method search all the items in repositoryMenu and transform then in DataListMenuItems, after that it returns it
    public List<DataListMenuItems> listMenuItems(int id) {
        return repositoryMenu.findById(id).stream().map(DataListMenuItems::new).collect(Collectors.toList());
    }
}
