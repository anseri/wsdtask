package com.wsd.ecommerce.task.wsd_task.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wsd.ecommerce.task.wsd_task.model.ItemDTO;
import com.wsd.ecommerce.task.wsd_task.service.ItemService;
import com.wsd.ecommerce.task.wsd_task.util.ReferencedException;
import com.wsd.ecommerce.task.wsd_task.util.ReferencedWarning;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/api/items", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemResource {

    private final ItemService itemService;

    public ItemResource(final ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        return ResponseEntity.ok(itemService.findAll());
    }

    @GetMapping("/top")
    public ResponseEntity<List<ItemDTO>> getTopSellingItems() {
        List<ItemDTO> topSellingItems = itemService.getTopSellingItems();
        return ResponseEntity.ok(topSellingItems);
    }
    
    @GetMapping("/top-by-date")
    public ResponseEntity<List<ItemDTO>> getTopSellingItems(@RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate)  {
        List<ItemDTO> topSellingItems = itemService.getTopSellingItemsByDate(startDate, endDate);
        return ResponseEntity.ok(topSellingItems);
    }
    
    @GetMapping("/top-last-month")
    public ResponseEntity<List<ItemDTO>> getTopSellingItemsOfLastMonth() {
    	List<ItemDTO> topSellingItems = itemService.getTopSellingItemsOfLastMonth();
        return ResponseEntity.ok(topSellingItems);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItem(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(itemService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createItem(@RequestBody @Valid final ItemDTO itemDTO) {
        final Long createdId = itemService.create(itemDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateItem(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final ItemDTO itemDTO) {
        itemService.update(id, itemDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteItem(@PathVariable(name = "id") final Long id) {
        final ReferencedWarning referencedWarning = itemService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
