package br.com.fatec.burguerboss.controller;

import br.com.fatec.burguerboss.domain.completedorder.ReportDataDTO;
import br.com.fatec.burguerboss.domain.completedorder.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public ResponseEntity<ReportDataDTO> getReportData() {
        return ResponseEntity.ok(reportService.generateReport());
    }
    
    @GetMapping("/period")
    public ResponseEntity<ReportDataDTO> getReportDataByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        
        return ResponseEntity.ok(reportService.generateReportByPeriod(startDate, endDate));
    }
}
