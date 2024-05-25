package com.wsd.ecommerce.task.wsd_task.model;

public class WishlistItemDTO {

    private Long id;
    private Long customer;
    private Long item;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustomer() {
		return customer;
	}
	public void setCustomer(Long customer) {
		this.customer = customer;
	}
	public Long getItem() {
		return item;
	}
	public void setItem(Long item) {
		this.item = item;
	}

}
