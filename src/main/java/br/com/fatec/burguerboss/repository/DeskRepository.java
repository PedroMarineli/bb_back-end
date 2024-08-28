package br.com.fatec.burguerboss.repository;

import br.com.fatec.burguerboss.models.Desk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeskRepository extends JpaRepository<Desk, Integer> {

    Desk findFirstByFilledIsFalse();
}
