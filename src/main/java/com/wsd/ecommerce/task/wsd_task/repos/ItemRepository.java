package com.wsd.ecommerce.task.wsd_task.repos;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wsd.ecommerce.task.wsd_task.domain.Item;


public interface ItemRepository extends JpaRepository<Item, Long> {
	
	@Query("SELECT SUM(item.price * item.quantitySold) FROM Item item WHERE DATE(item.saleDate) BETWEEN :startDate AND :endDate")
    Double findTotalSalesBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query("SELECT DATE(item.saleDate) FROM Item item WHERE DATE(item.saleDate) BETWEEN :startDate AND :endDate GROUP BY item.saleDate ORDER BY SUM(item.price * item.quantitySold) DESC LIMIT 1")
	LocalDate findMaxSaleDay(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	 @Query("SELECT item FROM Item item ORDER BY item.price * item.quantitySold DESC")
	 List<Item> findTopSellingItems(Pageable pageable);
	 
	 @Query("SELECT item FROM Item item WHERE DATE(item.saleDate) BETWEEN :startDate AND :endDate ORDER BY item.quantitySold DESC")
	 List<Item> findTopSellingItemsOfLastMonth(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

	 @Query("SELECT item FROM Item item WHERE DATE(item.saleDate) BETWEEN :startDate AND :endDate ORDER BY item.quantitySold DESC")
	 List<Item> findTopSellingItemsByDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

}
