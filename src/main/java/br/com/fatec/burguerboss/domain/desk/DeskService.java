package br.com.fatec.burguerboss.domain.desk;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DeskService {
    @Autowired
    private DeskRepository repository;

    public void verifyNumberOfTables(int numberOfTables) {
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

    public Page<DataListDesk> listDesk(Pageable pagination) {
        return repository.findAll(pagination).map(DataListDesk::new);
    }

    public void updateDesk(DataUpdateDesk data) {
        Desk desk = searchTableById(data.id());
        desk.changeStatus();
        repository.save(desk);
    }
}
