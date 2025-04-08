package br.com.fatec.burguerboss.controller;

import br.com.fatec.burguerboss.domain.menu.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    //call a menuService method to list all the menus(DataListMenu)
    @GetMapping
    public ResponseEntity<List<DataListMenu>> listMenu(){
        return ResponseEntity.ok().body(menuService.listMenu());
    }

    //call a menuService method to list menu items by id(menu)
    @GetMapping("/{id}")
    public ResponseEntity<DataListMenuItems> listMenuItems(@PathVariable @NotNull Integer id){
        return ResponseEntity.ok().body(menuService.listMenuItems(id));
    }

    //call a menuService method to create a new menu
    @PostMapping
    @Transactional
    public ResponseEntity<Void> createMenu(){
         menuService.createMenu();
         return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //call a menuService method to create a new menu item
    @PostMapping("/item")
    @Transactional
    public ResponseEntity<Void> createMenuItem(@RequestBody @Valid DataCreateMenuItem data){
        menuService.createMenuItem(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //call a menuService method to update a menu item
    @PutMapping("/item")
    @Transactional
    public ResponseEntity<Void> updateMenuItem(@RequestBody @Valid DataUpdateMenuItem data){
        menuService.updateMenuItem(data);
        return ResponseEntity.noContent().build();
    }

    //call a menuService method to delete a menu
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable @NotNull Integer id){
        menuService.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }

    //call a menuService method to delete a menu item
    @DeleteMapping("/item/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable @NotNull Integer id){
        menuService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
}
