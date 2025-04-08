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
    private DeskService deskService;

    @GetMapping
    public ResponseEntity<Page<DataListDesk>> listDesk(Pageable pagination){
        return ResponseEntity.ok().body(deskService.listDesk(pagination));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> registerDesk(@RequestBody @Valid DataCreateDesk data){
        deskService.verifyNumberOfTables(data.deskNumber());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Void> updateDesk(@RequestBody @Valid DataUpdateDesk data){
        deskService.updateDesk(data);
        return ResponseEntity.noContent().build();
    }
}
