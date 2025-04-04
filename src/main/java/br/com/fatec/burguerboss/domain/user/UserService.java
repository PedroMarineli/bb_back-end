package br.com.fatec.burguerboss.domain.user;

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
}
