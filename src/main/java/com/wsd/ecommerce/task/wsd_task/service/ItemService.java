package com.wsd.ecommerce.task.wsd_task.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.wsd.ecommerce.task.wsd_task.domain.Item;
import com.wsd.ecommerce.task.wsd_task.domain.WishlistItem;
import com.wsd.ecommerce.task.wsd_task.model.ItemDTO;
import com.wsd.ecommerce.task.wsd_task.repos.ItemRepository;
import com.wsd.ecommerce.task.wsd_task.repos.WishlistItemRepository;
import com.wsd.ecommerce.task.wsd_task.util.NotFoundException;
import com.wsd.ecommerce.task.wsd_task.util.ReferencedWarning;


@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final WishlistItemRepository wishlistItemRepository;

    public ItemService(final ItemRepository itemRepository,
            final WishlistItemRepository wishlistItemRepository) {
        this.itemRepository = itemRepository;
        this.wishlistItemRepository = wishlistItemRepository;
    }

    public List<ItemDTO> findAll() {
        final List<Item> items = itemRepository.findAll(Sort.by("id"));
        return items.stream()
                .map(item -> mapToDTO(item, new ItemDTO()))
                .collect(Collectors.toList());
    }

    public ItemDTO get(final Long id) {
        return itemRepository.findById(id)
                .map(item -> mapToDTO(item, new ItemDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ItemDTO itemDTO) {
        final Item item = new Item();
        mapToEntity(itemDTO, item);
        return itemRepository.save(item).getId();
    }

    public void update(final Long id, final ItemDTO itemDTO) {
        final Item item = itemRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(itemDTO, item);
        itemRepository.save(item);
    }

    public void delete(final Long id) {
        itemRepository.deleteById(id);
    }

    private ItemDTO mapToDTO(final Item item, final ItemDTO itemDTO) {
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setQuantitySold(item.getQuantitySold());
        itemDTO.setSaleDate(item.getSaleDate());
        return itemDTO;
    }

    private Item mapToEntity(final ItemDTO itemDTO, final Item item) {
        item.setName(itemDTO.getName());
        item.setPrice(itemDTO.getPrice());
        item.setQuantitySold(itemDTO.getQuantitySold());
        item.setSaleDate(itemDTO.getSaleDate());
        return item;
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Item item = itemRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final WishlistItem itemWishlistItem = wishlistItemRepository.findFirstByItem(item);
        if (itemWishlistItem != null) {
            referencedWarning.setKey("item.wishlistItem.item.referenced");
            referencedWarning.addParam(itemWishlistItem.getId());
            return referencedWarning;
        }
        return null;
    }

    public List<ItemDTO> getTopSellingItems() {
    	Pageable pageable = PageRequest.of(0, 5); // Fetch top 1 items
        return itemRepository.findTopSellingItems(pageable).stream()
                .map(item -> mapToDTO(item, new ItemDTO()))
                .collect(Collectors.toList());
    }
    
    public List<ItemDTO> getTopSellingItemsByDate(String startDateStr, String endDateStr) {
    	 LocalDate startDate = LocalDate.parse(startDateStr);
         LocalDate endDate = LocalDate.parse(endDateStr);

        // Convert LocalDate to Date
        Date start = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(endDate.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());

        Pageable pageable = PageRequest.of(0, 5); // Fetch top 1 items
        return itemRepository.findTopSellingItemsOfLastMonth(start, end, pageable).stream()
                .map(item -> mapToDTO(item, new ItemDTO()))
                .collect(Collectors.toList());
    }
    
    public List<ItemDTO> getTopSellingItemsOfLastMonth() {
        // Get the start and end dates of the last month
        LocalDate today = LocalDate.now();
        LocalDate startOfLastMonth = today.minusMonths(1).withDayOfMonth(1);
        LocalDate endOfLastMonth = today.withDayOfMonth(1).minusDays(1);

        // Convert LocalDate to Date
        Date startDate = Date.from(startOfLastMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endOfLastMonth.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());

        Pageable pageable = PageRequest.of(0, 5); // Fetch top 1 items
        return itemRepository.findTopSellingItemsOfLastMonth(startDate, endDate, pageable).stream()
                .map(item -> mapToDTO(item, new ItemDTO()))
                .collect(Collectors.toList());
    }
}
