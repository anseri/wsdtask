package com.wsd.ecommerce.task.wsd_task.rest;

import com.wsd.ecommerce.task.wsd_task.model.WishlistItemDTO;
import com.wsd.ecommerce.task.wsd_task.service.WishlistItemService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/wishlistItems", produces = MediaType.APPLICATION_JSON_VALUE)
public class WishlistItemResource {

    private final WishlistItemService wishlistItemService;

    public WishlistItemResource(final WishlistItemService wishlistItemService) {
        this.wishlistItemService = wishlistItemService;
    }

    @GetMapping
    public ResponseEntity<List<WishlistItemDTO>> getAllWishlistItems() {
        return ResponseEntity.ok(wishlistItemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishlistItemDTO> getWishlistItem(
            @PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(wishlistItemService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createWishlistItem(
            @RequestBody @Valid final WishlistItemDTO wishlistItemDTO) {
        final Long createdId = wishlistItemService.create(wishlistItemDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateWishlistItem(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final WishlistItemDTO wishlistItemDTO) {
        wishlistItemService.update(id, wishlistItemDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteWishlistItem(@PathVariable(name = "id") final Long id) {
        wishlistItemService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
