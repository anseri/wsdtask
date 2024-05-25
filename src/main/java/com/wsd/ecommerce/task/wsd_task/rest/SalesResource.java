package com.wsd.ecommerce.task.wsd_task.rest;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wsd.ecommerce.task.wsd_task.service.SalesService;

@RestController
@RequestMapping("/api/sales")
public class SalesResource {
    @Autowired
    private SalesService salesService;

    @GetMapping("/total")
    public ResponseEntity<?> getTotalSaleAmount() {
        double totalSaleAmount = salesService.getTotalSaleAmount();
        return ResponseEntity.ok(totalSaleAmount);
    }
    
    @GetMapping("/max-day")
    public ResponseEntity<?> getMaxSaleDay(@RequestParam("startDate") String startDate,
                                           @RequestParam("endDate") String endDate) {
        LocalDate maxSaleDay = salesService.getMaxSaleDay(startDate, endDate);
        return ResponseEntity.ok(maxSaleDay);
    }
}