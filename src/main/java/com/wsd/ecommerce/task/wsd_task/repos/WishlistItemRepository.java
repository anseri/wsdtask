package com.wsd.ecommerce.task.wsd_task.repos;

import com.wsd.ecommerce.task.wsd_task.domain.Customer;
import com.wsd.ecommerce.task.wsd_task.domain.Item;
import com.wsd.ecommerce.task.wsd_task.domain.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {

    WishlistItem findFirstByCustomer(Customer customer);

    WishlistItem findFirstByItem(Item item);

}
