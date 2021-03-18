package com.poc.domain;

import java.util.List;

/**
 * @author
 */
public class Order {

	private List<Product> products;

	private List<Product> freeBeverages;
	
	private List<Product> freeExtras;

	private Double totalAmount;

	private Double discounts;
	
	private Double amountToPay;

	public Order() {
		super();
	}

	public Order(List<Product> products) {
		super();
		this.products = products;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public List<Product> getFreeBeverages() {
		return this.freeBeverages;
	}

	public void setFreeBeverages(List<Product> freeBeverages) {
		this.freeBeverages = freeBeverages;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Product> getFreeExtras() {
		return this.freeExtras;
	}

	public void setFreeExtras(List<Product> freeExtras) {
		this.freeExtras = freeExtras;
	}

	public Double getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getDiscounts() {
		return discounts;
	}

	public void setDiscounts(Double discounts) {
		this.discounts = discounts;
	}

	public Double getAmountToPay() {
		return amountToPay;
	}

	public void setAmountToPay(Double amountToPay) {
		this.amountToPay = amountToPay;
	}

}