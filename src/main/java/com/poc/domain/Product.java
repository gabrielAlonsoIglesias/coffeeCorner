package com.poc.domain;

import java.util.Objects;

public class Product {

	private Integer id;

	private String name;

	private EProductType type;

	private String size;

	private Double price;

	private String currency;

	public Product() {
		super();
	}

	public Product(String name, EProductType type, String size, Double price, String currency) {
		super();
		this.name = name;
		this.type = type;
		this.size = size;
		this.price = price;
		this.currency = currency;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EProductType getType() {
		return this.type;
	}

	public void setType(EProductType type) {
		this.type = type;
	}

	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		String result = null;
		if (this.size != null && this.size != "") {
			result = String.format("%s (%s) %s %s",
					 			   Objects.toString(this.name),
					 			   Objects.toString(this.size),
					 			   Objects.toString(this.price),
					 			   Objects.toString(this.currency));
		} else {
			result=  String.format("%s %s %s",
								   Objects.toString(this.name),
								   Objects.toString(this.price),
								   Objects.toString(this.currency));			
		}
		return result;
	}

}
