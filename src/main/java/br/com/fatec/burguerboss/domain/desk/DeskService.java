package br.com.fatec.burguerboss.domain.desk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DeskService {
    @Autowired
    private DeskRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(DeskService.class);

    public void verifyNumberOfTables(int numberOfTables) {
        try {
            logger.info("Verificando número de mesas. Número desejado: {}", numberOfTables);
            if (repository.count() < numberOfTables){
                while (repository.count()!= numberOfTables){
                    createTable();
                }
            } else if (repository.count() > numberOfTables) {
                while (repository.count()!= numberOfTables){
                    deleteTable();
                }
            }
        } catch (Exception e) {
            logger.error("Erro ao verificar número de mesas: {}", e.getMessage());
            throw new RuntimeException("Erro ao verificar número de mesas.", e);
        }
    } //verifica o numero de mesas existentes no banco de dados

    private void deleteTable() {
        try {
            Desk deletedDesk = repository.findFirstByFilledIsFalse();
            repository.delete(deletedDesk);
        } catch (Exception e) {
            logger.error("Erro ao deletar mesa: {}", e.getMessage());
            throw new RuntimeException("Erro ao deletar mesa.", e);
        }
    } //Remove apenas um objeto onde o atributo e falso

    public void createTable(){
        try {
            Desk mesa = new Desk(false);
            repository.save(mesa);
        } catch (Exception e) {
            logger.error("Erro ao criar mesa: {}", e.getMessage());
            throw new RuntimeException("Erro ao criar mesa.", e);
        }
    } //instancia uma nova mesa e adiciona ela na lista e no banco de dados

    public Desk searchTableById(int id){
        try {
            Optional<Desk> desk = repository.findById(id);
            return desk.orElse(null);
        } catch (Exception e) {
            logger.error("Erro ao buscar mesa com ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Erro ao buscar mesa.", e);
        }
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
