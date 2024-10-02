package br.com.fatec.burguerboss.desk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/desk")
public class DeskController {

    @Autowired
    private DeskRepository repository;

    @GetMapping
    public Page<DataListDesk> listDesk(Pageable pagination){
        return repository.findAll(pagination).map(DataListDesk::new);
    }

    @PostMapping
    @Transactional
    public void registerDesk(DataListDesk data){
        repository.save(new Desk(data));
    }
}
