package br.com.fatec.burguerboss.domain.completedorder;

import java.math.BigDecimal;
import java.util.List;

public record ReportDataDTO(
    List<MenuItemReportDTO> mostOrderedItems,
    List<MenuItemReportDTO> leastOrderedItems,
    BigDecimal totalRevenue
) {}
