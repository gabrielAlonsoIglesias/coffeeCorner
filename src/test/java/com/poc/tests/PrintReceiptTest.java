package com.poc.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.poc.Application;
import com.poc.domain.EProductType;
import com.poc.domain.Order;
import com.poc.domain.Product;


/**
 * @author galonsoi
 */
public class PrintReceiptTest {
	
	private Order order;
	
	private Product product;

	private List<Product> products;

	private List<Product> freeBeverages;
	
	private List<Product> freeExtras;

	@BeforeEach
	public void setup() {
		this.products = new ArrayList<Product>();
		this.freeBeverages = new ArrayList<Product>();
		this.freeExtras = new ArrayList<Product>();
		this.product = new Product("mock", EProductType.BEVERAGE, "5", 4.6, "EUR");
		this.order = new Order();
		this.order.setProducts(new ArrayList<>());
		this.order.setFreeBeverages(new ArrayList<>());
		this.order.setFreeExtras(new ArrayList<>());
		this.order.setTotalAmount(4.5);
		this.order.setDiscounts(7.5);
		this.order.setAmountToPay(3.0);
	}

	@Test
    public void test_printReceipt_01() {
   		Application.printReceipt(this.order);

   		assertTrue(true);
    }
	
	@Test
    public void test_printReceipt_02() {
		this.order.getProducts().clear();
		this.order.getFreeBeverages().clear();
		this.order.getFreeExtras().clear();
		this.products.add(this.product);
		this.freeBeverages.add(this.product);
		this.freeExtras.add(this.product);
		this.freeExtras.add(this.product);
		this.order.setProducts(this.products);
		this.order.setFreeBeverages(this.freeBeverages);
		this.order.setFreeExtras(this.freeExtras);

   		Application.printReceipt(this.order);

   		assertTrue(true);
    }

}