package br.com.fatec.burguerboss.domain.user;

import br.com.fatec.burguerboss.domain.menu.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    UserDetails findByUsername(String username);
}
