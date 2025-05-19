package br.com.fatec.burguerboss.domain.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public Page<DataListUser> listUsers(Pageable pagination) {
        return repository.findAll(pagination).map(DataListUser::new);
    }

    public void createUser(@Valid DataCreateUser dataCreateUser) {
        try {
            logger.info("Criando novo usuário: {}", dataCreateUser.username());
            User user = new User(dataCreateUser);
            repository.save(user);
        } catch (Exception e) {
            logger.error("Erro ao criar usuário: {}", e.getMessage());
            throw new RuntimeException("Erro ao criar usuário.", e);
        }
    }

    public void updateUser(@Valid DataUpdateUser dataUpdateUser) {
        try {
            logger.info("Atualizando usuário com ID: {}", dataUpdateUser.id());
            User user = repository.findById(dataUpdateUser.id())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + dataUpdateUser.id()));
            user.setUsername(dataUpdateUser.username());
            user.setPassword(dataUpdateUser.password());
            user.setRole(dataUpdateUser.role());
            repository.save(user);
        } catch (Exception e) {
            logger.error("Erro ao atualizar usuário: {}", e.getMessage());
            throw new RuntimeException("Erro ao atualizar usuário.", e);
        }
    }

    public void deleteUser(Integer id) {
        try {
            logger.info("Deletando usuário com ID: {}", id);
            repository.deleteById(id);
        } catch (Exception e) {
            logger.error("Erro ao deletar usuário: {}", e.getMessage());
            throw new RuntimeException("Erro ao deletar usuário com o ID: " + id, e);
        }
    }
}
