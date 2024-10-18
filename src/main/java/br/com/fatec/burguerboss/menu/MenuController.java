package br.com.fatec.burguerboss.menu;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<DataListMenu> listMenu(){
        return menuService.listMenuItem();
    }

    @PostMapping("/item")
    @Transactional
    public void registerMenuItem(@RequestBody DataCreateMenuItem data){
        menuService.registerMenuItem(data);
    }

    @PostMapping
    @Transactional
    public void registerMenu(){
        menuService.registerMenu();
    }

    @PutMapping("/item")
    @Transactional
    public void updateMenuItem(@RequestBody DataUpdateMenuItem data){
        menuService.updateMenuItem(data);
    }
}
