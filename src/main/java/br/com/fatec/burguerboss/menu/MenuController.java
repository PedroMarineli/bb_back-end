package br.com.fatec.burguerboss.menu;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    //call a menuService method to list menu items by id(menu)
    @GetMapping("/{id}")
    public List<DataListMenuItems> listMenuItens(@PathVariable @NotNull Integer id){
        return menuService.listMenuItems(id);
    }
}
