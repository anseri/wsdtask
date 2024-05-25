package com.wsd.ecommerce.task.wsd_task.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.wsd.ecommerce.task.wsd_task.domain.Customer;
import com.wsd.ecommerce.task.wsd_task.domain.Item;
import com.wsd.ecommerce.task.wsd_task.domain.WishlistItem;
import com.wsd.ecommerce.task.wsd_task.model.WishlistItemDTO;
import com.wsd.ecommerce.task.wsd_task.repos.CustomerRepository;
import com.wsd.ecommerce.task.wsd_task.repos.ItemRepository;
import com.wsd.ecommerce.task.wsd_task.repos.WishlistItemRepository;
import com.wsd.ecommerce.task.wsd_task.util.NotFoundException;


@Service
public class WishlistItemService {

    private final WishlistItemRepository wishlistItemRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;

    public WishlistItemService(final WishlistItemRepository wishlistItemRepository,
            final CustomerRepository customerRepository, final ItemRepository itemRepository) {
        this.wishlistItemRepository = wishlistItemRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
    }

    public List<WishlistItemDTO> findAll() {
        final List<WishlistItem> wishlistItems = wishlistItemRepository.findAll(Sort.by("id"));
        return wishlistItems.stream()
                .map(wishlistItem -> mapToDTO(wishlistItem, new WishlistItemDTO()))
                .collect(Collectors.toList());
    }

    public WishlistItemDTO get(final Long id) {
        return wishlistItemRepository.findById(id)
                .map(wishlistItem -> mapToDTO(wishlistItem, new WishlistItemDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final WishlistItemDTO wishlistItemDTO) {
        final WishlistItem wishlistItem = new WishlistItem();
        mapToEntity(wishlistItemDTO, wishlistItem);
        return wishlistItemRepository.save(wishlistItem).getId();
    }

    public void update(final Long id, final WishlistItemDTO wishlistItemDTO) {
        final WishlistItem wishlistItem = wishlistItemRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(wishlistItemDTO, wishlistItem);
        wishlistItemRepository.save(wishlistItem);
    }

    public void delete(final Long id) {
        wishlistItemRepository.deleteById(id);
    }

    private WishlistItemDTO mapToDTO(final WishlistItem wishlistItem,
            final WishlistItemDTO wishlistItemDTO) {
        wishlistItemDTO.setId(wishlistItem.getId());
        wishlistItemDTO.setCustomer(wishlistItem.getCustomer() == null ? null : wishlistItem.getCustomer().getId());
        wishlistItemDTO.setItem(wishlistItem.getItem() == null ? null : wishlistItem.getItem().getId());
        return wishlistItemDTO;
    }

    private WishlistItem mapToEntity(final WishlistItemDTO wishlistItemDTO,
            final WishlistItem wishlistItem) {
        final Customer customer = wishlistItemDTO.getCustomer() == null ? null : customerRepository.findById(wishlistItemDTO.getCustomer())
                .orElseThrow(() -> new NotFoundException("customer not found"));
        wishlistItem.setCustomer(customer);
        final Item item = wishlistItemDTO.getItem() == null ? null : itemRepository.findById(wishlistItemDTO.getItem())
                .orElseThrow(() -> new NotFoundException("item not found"));
        wishlistItem.setItem(item);
        return wishlistItem;
    }

}
