package com.mycompany.ecommerce.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPurchaseOrder implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// ---------- @EmbeddedId, since it is an ID inside of auxiliary class
	@JsonIgnore
	@EmbeddedId
	private ItemPurchaseOrderPk id = new ItemPurchaseOrderPk();
	
	private Double discount;
	
	private Integer quantity;
	
	private Double price;
	
	public ItemPurchaseOrder() {
		
	}

	public ItemPurchaseOrder(PurchaseOrder purchaseOrder, Product product, Double discount, Integer quantity, Double price) {
		super();
		id.setPurchaseOrder(purchaseOrder);
		id.setProduct(product);
		this.discount = discount;
		this.quantity = quantity;
		this.price = price;
	}

	@JsonIgnore
	public PurchaseOrder getPurchaseOrder() {
		return id.getPurchaseOrder();
	}
	
	@JsonIgnore
	public Product getProduct() {
		return id.getProduct();
	}
	
	public ItemPurchaseOrderPk getId() {
		return id;
	}

	public void setId(ItemPurchaseOrderPk id) {
		this.id = id;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPurchaseOrder other = (ItemPurchaseOrder) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}