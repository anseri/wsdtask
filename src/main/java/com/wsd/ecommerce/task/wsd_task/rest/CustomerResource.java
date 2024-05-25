package com.wsd.ecommerce.task.wsd_task.rest;

import java.util.List;
import java.util.NoSuchElementException;

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
import org.springframework.web.bind.annotation.RestController;

import com.wsd.ecommerce.task.wsd_task.model.CustomerDTO;
import com.wsd.ecommerce.task.wsd_task.model.ItemDTO;
import com.wsd.ecommerce.task.wsd_task.service.CustomerService;
import com.wsd.ecommerce.task.wsd_task.util.ReferencedException;
import com.wsd.ecommerce.task.wsd_task.util.ReferencedWarning;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/api/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerResource {

    private final CustomerService customerService;

    public CustomerResource(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{customerId}/wishlist")
    public ResponseEntity<List<ItemDTO>> getCustomerWishlist(@PathVariable(name ="customerId") final Long customerId) {
        try {
            List<ItemDTO> wishlist = customerService.getCustomerWishlist(customerId);
            return ResponseEntity.ok(wishlist);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(customerService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createCustomer(@RequestBody @Valid final CustomerDTO customerDTO) {
        final Long createdId = customerService.create(customerDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateCustomer(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final CustomerDTO customerDTO) {
        customerService.update(id, customerDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCustomer(@PathVariable(name = "id") final Long id) {
        final ReferencedWarning referencedWarning = customerService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
