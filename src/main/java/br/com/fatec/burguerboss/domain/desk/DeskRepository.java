package br.com.fatec.burguerboss.domain.desk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeskRepository extends JpaRepository<Desk, Integer> {
    Desk findFirstByFilledIsFalse();
}
