package br.com.fatec.burguerboss.domain.completedorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private CompletedOrderRepository completedOrderRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);

    public ReportDataDTO generateReport() {
        try {
            logger.info("Gerando relatório de dados");
            
            List<MenuItemReportDTO> mostOrderedItems = convertToDTO(completedOrderRepository.findMostOrderedItems());
            List<MenuItemReportDTO> leastOrderedItems = convertToDTO(completedOrderRepository.findLeastOrderedItems());
            BigDecimal totalRevenue = completedOrderRepository.calculateTotalRevenue();
            
            logger.info("Relatório gerado com sucesso. Total de receita: {}", totalRevenue);
            
            return new ReportDataDTO(
                mostOrderedItems,
                leastOrderedItems,
                totalRevenue != null ? totalRevenue : BigDecimal.ZERO
            );
        } catch (Exception e) {
            logger.error("Erro ao gerar relatório: {}", e.getMessage());
            
            // Em caso de erro, retorna um relatório vazio
            return new ReportDataDTO(
                new ArrayList<>(),
                new ArrayList<>(),
                BigDecimal.ZERO
            );
        }
    }
    
    // Novo método para gerar relatório por período
    public ReportDataDTO generateReportByPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        try {
            logger.info("Gerando relatório de dados para o período de {} a {}", startDate, endDate);
            
            List<MenuItemReportDTO> mostOrderedItems = convertToDTO(
                completedOrderRepository.findMostOrderedItemsByPeriod(startDate, endDate));
            List<MenuItemReportDTO> leastOrderedItems = convertToDTO(
                completedOrderRepository.findLeastOrderedItemsByPeriod(startDate, endDate));
            BigDecimal totalRevenue = completedOrderRepository.calculateTotalRevenueByPeriod(startDate, endDate);
            
            logger.info("Relatório por período gerado com sucesso. Total de receita: {}", totalRevenue);
            
            return new ReportDataDTO(
                mostOrderedItems,
                leastOrderedItems,
                totalRevenue != null ? totalRevenue : BigDecimal.ZERO
            );
        } catch (Exception e) {
            logger.error("Erro ao gerar relatório por período: {}", e.getMessage());
            
            // Em caso de erro, retorna um relatório vazio
            return new ReportDataDTO(
                new ArrayList<>(),
                new ArrayList<>(),
                BigDecimal.ZERO
            );
        }
    }

    private List<MenuItemReportDTO> convertToDTO(List<Object[]> results) {
        if (results == null || results.isEmpty()) {
            return new ArrayList<>();
        }
        
        return results.stream()
            .map(result -> new MenuItemReportDTO(
                (Integer) result[0],
                (String) result[1],
                ((Number) result[2]).longValue()
            ))
            .collect(Collectors.toList());
    }
}
