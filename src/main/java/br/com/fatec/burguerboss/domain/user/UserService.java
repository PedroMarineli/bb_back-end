package br.com.fatec.burguerboss.domain.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Page<DataListUser> listUsers(Pageable pagination) {
        return repository.findAll(pagination).map(DataListUser::new);
    }

    public void createUser(@Valid DataCreateUser dataCreateUser) {
        User user = new User(dataCreateUser);
        repository.save(user);
    }
}
