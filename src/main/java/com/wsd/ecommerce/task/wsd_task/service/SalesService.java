package com.wsd.ecommerce.task.wsd_task.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsd.ecommerce.task.wsd_task.repos.ItemRepository;

@Service
public class SalesService {
	 @Autowired
    private ItemRepository itemRepository;

    public double getTotalSaleAmount() {
    	 // Get the start and end times of the current day
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);

        // Convert LocalDateTime to Date
        Date startDate = Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());

        // Use the repository to get the sum of sales for the current day
        Double totalSales = itemRepository.findTotalSalesBetweenDates(startDate, endDate);
        return totalSales != null ? totalSales : 0.0;
    }

    public LocalDate getMaxSaleDay(String startDateStr, String endDateStr) {
    	  // Parse the input date strings to LocalDate
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        // Convert LocalDate to Date
        Date start = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(endDate.atTime(LocalDateTime.MAX.toLocalTime()).atZone(ZoneId.systemDefault()).toInstant());

        // Use the repository to find the day with the maximum sales
        LocalDate maxSaleDate = itemRepository.findMaxSaleDay(start, end);
        return maxSaleDate;
    
    }
}
