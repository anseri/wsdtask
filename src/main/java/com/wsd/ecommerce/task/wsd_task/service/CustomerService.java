package com.wsd.ecommerce.task.wsd_task.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.wsd.ecommerce.task.wsd_task.domain.Customer;
import com.wsd.ecommerce.task.wsd_task.domain.Item;
import com.wsd.ecommerce.task.wsd_task.domain.WishlistItem;
import com.wsd.ecommerce.task.wsd_task.model.CustomerDTO;
import com.wsd.ecommerce.task.wsd_task.model.ItemDTO;
import com.wsd.ecommerce.task.wsd_task.repos.CustomerRepository;
import com.wsd.ecommerce.task.wsd_task.repos.WishlistItemRepository;
import com.wsd.ecommerce.task.wsd_task.util.NotFoundException;
import com.wsd.ecommerce.task.wsd_task.util.ReferencedWarning;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final WishlistItemRepository wishlistItemRepository;

    public CustomerService(final CustomerRepository customerRepository,
            final WishlistItemRepository wishlistItemRepository) {
        this.customerRepository = customerRepository;
        this.wishlistItemRepository = wishlistItemRepository;
    }

    public List<CustomerDTO> findAll() {
        final List<Customer> customers = customerRepository.findAll(Sort.by("id"));
        return customers.stream()
                .map(customer -> mapToDTO(customer, new CustomerDTO()))
                .collect(Collectors.toList());
    }

    public CustomerDTO get(final Long id) {
        return customerRepository.findById(id)
                .map(customer -> mapToDTO(customer, new CustomerDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final CustomerDTO customerDTO) {
        final Customer customer = new Customer();
        mapToEntity(customerDTO, customer);
        return customerRepository.save(customer).getId();
    }

    public void update(final Long id, final CustomerDTO customerDTO) {
        final Customer customer = customerRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(customerDTO, customer);
        customerRepository.save(customer);
    }

    public void delete(final Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO mapToDTO(final Customer customer, final CustomerDTO customerDTO) {
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        return customerDTO;
    }

    private Customer mapToEntity(final CustomerDTO customerDTO, final Customer customer) {
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        return customer;
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Customer customer = customerRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final WishlistItem customerWishlistItem = wishlistItemRepository.findFirstByCustomer(customer);
        if (customerWishlistItem != null) {
            referencedWarning.setKey("customer.wishlistItem.customer.referenced");
            referencedWarning.addParam(customerWishlistItem.getId());
            return referencedWarning;
        }
        return null;
    }

    public List<ItemDTO> getCustomerWishlist(Long customerId) {
        System.out.println("customerId===="+customerId);

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found with id: " + customerId));
        
        System.out.println("customer===="+customer);
        System.out.println("customergetName===="+customer.getName());
        System.out.println("getEmail===="+customer.getEmail());

        System.out.println("customergetWishlistItems===="+customer.getWishlistItems());
        System.out.println("customergetWishlistItemssize===="+customer.getWishlistItems().size());

        List<Item> items = customer.getWishlistItems().stream()
                .map(WishlistItem::getItem)
                .collect(Collectors.toList());
        
        return items.stream()
                .map(item -> mapToItemDTO(item, new ItemDTO()))
                .collect(Collectors.toList());
    }
    
    private ItemDTO mapToItemDTO(final Item item, final ItemDTO itemDTO) {
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setQuantitySold(item.getQuantitySold());
        itemDTO.setSaleDate(item.getSaleDate());
        return itemDTO;
    }

}
