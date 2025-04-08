package br.com.fatec.burguerboss.controller;

import br.com.fatec.burguerboss.domain.user.DataCreateUser;
import br.com.fatec.burguerboss.domain.user.DataListUser;
import br.com.fatec.burguerboss.domain.user.DataUpdateUser;
import br.com.fatec.burguerboss.domain.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<Page<DataListUser>> listUsers(Pageable pagination){
        return ResponseEntity.ok().body(service.listUsers(pagination));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> createUser(@RequestBody @Valid DataCreateUser dataCreateUser){
        service.createUser(dataCreateUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Void> updateUser(@RequestBody @Valid DataUpdateUser dataUpdateUser){
        service.updateUser(dataUpdateUser);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
