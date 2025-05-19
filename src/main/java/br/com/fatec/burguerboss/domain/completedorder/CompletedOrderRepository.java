package br.com.fatec.burguerboss.domain.completedorder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CompletedOrderRepository extends JpaRepository<CompletedOrder, Integer> {
    
    // Consultas atualizadas para usar apenas a tabela de pedidos concluídos
    @Query(value = "SELECT coi.menu_item_id, coi.menu_item_name, SUM(coi.quantity) as total_quantity " +
            "FROM completed_order_items coi " +
            "GROUP BY coi.menu_item_id, coi.menu_item_name " +
            "ORDER BY total_quantity DESC", nativeQuery = true)
    List<Object[]> findMostOrderedItems();
    
    @Query(value = "SELECT coi.menu_item_id, coi.menu_item_name, SUM(coi.quantity) as total_quantity " +
            "FROM completed_order_items coi " +
            "GROUP BY coi.menu_item_id, coi.menu_item_name " +
            "ORDER BY total_quantity ASC", nativeQuery = true)
    List<Object[]> findLeastOrderedItems();
    
    @Query("SELECT SUM(co.totalValue) FROM CompletedOrder co")
    BigDecimal calculateTotalRevenue();
    
    // Consultas por período atualizadas
    @Query(value = "SELECT coi.menu_item_id, coi.menu_item_name, SUM(coi.quantity) as total_quantity " +
            "FROM completed_order_items coi " +
            "JOIN pedidos_concluidos pc ON coi.completed_order_id = pc.id " +
            "WHERE pc.completion_date BETWEEN :startDate AND :endDate " +
            "GROUP BY coi.menu_item_id, coi.menu_item_name " +
            "ORDER BY total_quantity DESC", nativeQuery = true)
    List<Object[]> findMostOrderedItemsByPeriod(
            @Param("startDate") LocalDateTime startDate, 
            @Param("endDate") LocalDateTime endDate);
    
    @Query(value = "SELECT coi.menu_item_id, coi.menu_item_name, SUM(coi.quantity) as total_quantity " +
            "FROM completed_order_items coi " +
            "JOIN pedidos_concluidos pc ON coi.completed_order_id = pc.id " +
            "WHERE pc.completion_date BETWEEN :startDate AND :endDate " +
            "GROUP BY coi.menu_item_id, coi.menu_item_name " +
            "ORDER BY total_quantity ASC", nativeQuery = true)
    List<Object[]> findLeastOrderedItemsByPeriod(
            @Param("startDate") LocalDateTime startDate, 
            @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT SUM(co.totalValue) FROM CompletedOrder co WHERE co.completionDate BETWEEN :startDate AND :endDate")
    BigDecimal calculateTotalRevenueByPeriod(
            @Param("startDate") LocalDateTime startDate, 
            @Param("endDate") LocalDateTime endDate);
}
