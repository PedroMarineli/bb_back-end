package br.com.fatec.burguerboss.domain.menu;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    Page<MenuItem> findByMenuId(Integer menuId, Pageable pageable);
}
