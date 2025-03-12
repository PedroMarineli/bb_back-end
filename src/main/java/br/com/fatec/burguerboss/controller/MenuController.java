package br.com.fatec.burguerboss.controller;

import br.com.fatec.burguerboss.domain.menu.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    //call a menuService method to list all the menus(DataListMenu)
    @GetMapping
    public List<DataListMenu> listMenu(){
        return menuService.listMenu();
    }

    //call a menuService method to list menu items by id(menu)
    @GetMapping("/{id}")
    public DataListMenuItems listMenuItems(@PathVariable @NotNull Integer id){
        return menuService.listMenuItems(id);
    }

    //call a menuService method to create a new menu
    @PostMapping
    @Transactional
    public void createMenu(){
         menuService.createMenu();
    }

    //call a menuService method to create a new menu item
    @PostMapping("/item")
    @Transactional
    public void createMenuItem(@RequestBody @Valid DataCreateMenuItem data){
        menuService.createMenuItem(data);
    }

    //call a menuService method to update a menu item
    @PutMapping("/item")
    @Transactional
    public void updateMenuItem(@RequestBody @Valid DataUpdateMenuItem data){
        menuService.updateMenuItem(data);
    }

    //call a menuService method to delete a menu
    @DeleteMapping("/{id}")
    public void deleteMenu(@PathVariable @NotNull Integer id){
        menuService.deleteMenu(id);
    }

    //call a menuService method to delete a menu item
    @DeleteMapping("/item/{id}")
    public void deleteMenuItem(@PathVariable @NotNull Integer id){
        menuService.deleteMenuItem(id);
    }
}
