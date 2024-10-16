package br.com.fatec.burguerboss.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuItemRepository repository;

    @GetMapping
    public Page<DataListMenuItem> listMenu(Pageable pagination){
        return repository.findAll(pagination).map(DataListMenuItem::new);
    }
}
