package br.com.fatec.burguerboss.controller;

import br.com.fatec.burguerboss.domain.desk.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/desk")
public class DeskController {

    @Autowired
    private DeskRepository repository;

    @GetMapping
    public ResponseEntity<Page<DataListDesk>> listDesk(Pageable pagination){
        return ResponseEntity.ok().body(repository.findAll(pagination).map(DataListDesk::new));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> registerDesk(@RequestBody @Valid DataCreateDesk data){
        verifyNumberOfTables(data.deskNumber());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Void> updateDesk(@RequestBody @Valid DataUpdateDesk data){
        Desk desk = searchTableById(data.id());
        desk.changeStatus();
        repository.save(desk);
        return ResponseEntity.ok().build();
    }

    private void verifyNumberOfTables(int numberOfTables) {
        if (repository.count() < numberOfTables){
            while (repository.count()!= numberOfTables){
                createTable();
            }
        } else if (repository.count() > numberOfTables) {
            while (repository.count()!= numberOfTables){
                deleteTable();
            }
        }
    } //verifica o numero de mesas existentes no banco de dados

    private void deleteTable() {
        Desk deletedDesk = repository.findFirstByFilledIsFalse();
        repository.delete(deletedDesk);
    } //Remove apenas um objeto onde o atributo e falso

    public void createTable(){
        Desk mesa = new Desk(false);
        repository.save(mesa);
    } //instancia uma nova mesa e adiciona ela na lista e no banco de dados

    public Desk searchTableById(int id){
        Optional<Desk> desk = repository.findById(id);
        return desk.orElse(null);
    } //retorna uma mesa pelo Id
}
