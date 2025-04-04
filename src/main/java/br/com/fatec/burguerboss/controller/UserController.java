package br.com.fatec.burguerboss.controller;

import br.com.fatec.burguerboss.domain.user.DataListUser;
import br.com.fatec.burguerboss.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<Page<DataListUser>> listUsers(Pageable pagination){
        return ResponseEntity.ok().body(service.listUsers(pagination));
    }
}
